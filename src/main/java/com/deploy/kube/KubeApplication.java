package com.deploy.kube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KubeApplication {

	public static void main(String[] args) {
		SpringApplication.run(KubeApplication.class, args);
		System.out.println("Its Running....");
		System.out.println("CI CD is Running......");
		System.out.println("Checking ......");
	}

}
