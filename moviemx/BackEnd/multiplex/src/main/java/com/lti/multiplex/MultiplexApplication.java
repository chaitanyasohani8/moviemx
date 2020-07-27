package com.lti.multiplex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableMongoRepositories
@EnableEurekaClient
@EnableFeignClients("com.lti.multiplex.feignproxy")
public class MultiplexApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiplexApplication.class, args);
	}

}
