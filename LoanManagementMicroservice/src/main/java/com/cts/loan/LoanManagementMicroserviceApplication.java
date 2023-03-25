package com.cts.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * this is the main class
 * @EnableFeignClients to enable component scanning of the interfaces which are feign clients
 *
 */
@SpringBootApplication
@EnableFeignClients
@Slf4j
public class LoanManagementMicroserviceApplication {

	public static void main(String[] args) {
		log.info("Main function is called");
		SpringApplication.run(LoanManagementMicroserviceApplication.class, args);
		log.info("Main function ended");
	}

}
