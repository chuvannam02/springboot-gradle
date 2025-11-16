# Build stage
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Copy gradle wrapper and properties first for better caching
COPY gradlew gradlew.bat build.gradle ./
COPY gradle/ gradle/

# Download Gradle distribution (cached)
RUN --mount=type=cache,target=/root/.gradle ./gradlew --version

# Copy source code
COPY src/ src/

# Build the application
RUN --mount=type=cache,target=/root/.gradle ./gradlew --no-daemon clean bootJar \
    -Dspring-framework.version=6.2.11 \
    -Dtomcat.version=10.1.47
# Extract the application dependencies
RUN jar xf build/libs/springboot-gradle-0.0.1-SNAPSHOT.jar

# Analyze the dependencies contained into the fat jar
RUN jdeps --ignore-missing-deps -q  \
  --recursive  \
  --multi-release 17  \
  --print-module-deps  \
  --class-path 'BOOT-INF/lib/*'  \
  build/libs/springboot-gradle-0.0.1-SNAPSHOT.jar > deps.info

#💡 Tip: JDK 17 (eclipse-temurin) hỗ trợ compress 0, 1, 2 thôi, không có zip-9.
#Nguyên nhân
#Tùy chọn --compress của jlink chỉ nhận các giá trị:
#0 → no compression
#1 → minimal compression (fastest)
#2 → maximal compression (smallest)
# --multi-release 17  \ => --compress 2
# --multi-release 21 \ => --compress zip-9
# Create the custom JRE
RUN jlink \
  --verbose \
  --add-modules $(cat deps.info) \
  --compress 2 \
  --no-header-files \
  --no-man-pages \
  --output /custom_jre

# Healthcheck stage
FROM busybox:1.36.0-musl AS healthcheck

# Runtime stage
FROM gcr.io/distroless/base-debian12
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="$JAVA_HOME/bin:$PATH"
COPY --from=build /custom_jre $JAVA_HOME

# Copy wget for healthcheck
COPY --from=healthcheck /bin/wget /usr/bin/wget

WORKDIR /app

# Copy application insights config
#COPY lib/applicationinsights.json ./

# Copy the built JAR
COPY --from=build /app/build/libs/springboot-gradle-0.0.1-SNAPSHOT.jar /app.jar

# Add labels
LABEL org.opencontainers.image.source="https://github.com/hmcts/spring-boot-template" \
  org.opencontainers.image.version="0.0.1" \
  org.opencontainers.image.licenses="MIT"

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=3s --start-period=10s --retries=3 \
  CMD ["/usr/bin/wget", "--quiet", "--output-document=/dev/null", "http://localhost:8080/health"]

CMD ["java", "-jar", "/app.jar"]