package com.learning.springboot_gradle.grpc;

import com.example.grpc.HelloRequest;
import com.example.grpc.HelloResponse;
import com.example.grpc.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Component;

/**
 * @Project: springboot-gradle
 * @Package: com.learning.springboot_gradle.grpc  *
 * @Author: ChuVanNam
 * @Date: 11/17/2025
 * @Time: 3:27 PM
 */


@Component
public class GrpcClient {

	private final HelloServiceGrpc.HelloServiceBlockingStub stub;

	public GrpcClient() {
		ManagedChannel channel = ManagedChannelBuilder
				.forAddress("localhost", 9090)
				.usePlaintext()
				.build();

		stub = HelloServiceGrpc.newBlockingStub(channel);
	}

	public String sayHello(String name) {
		HelloRequest request = HelloRequest.newBuilder().setName(name).build();
		HelloResponse response = stub.sayHello(request);
		return response.getMessage();
	}
}
