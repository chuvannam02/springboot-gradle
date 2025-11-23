package com.learning.springboot_gradle.grpc;

import com.example.grpc.HelloRequest;
import com.example.grpc.HelloResponse;
import com.example.grpc.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

/**
 * @Project: springboot-gradle
 * @Package: com.learning.springboot_gradle.grpc  *
 * @Author: ChuVanNam
 * @Date: 11/17/2025
 * @Time: 3:27 PM
 */

@GrpcService
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

	@Override
	public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
		String name = request.getName();
		HelloResponse response = HelloResponse.newBuilder()
				.setMessage("Hello, " + name + "!")
				.build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
}
