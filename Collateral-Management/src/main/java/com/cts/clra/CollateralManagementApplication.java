package com.cts.clra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableFeignClients
@Slf4j
public class CollateralManagementApplication {

	public static void main(String[] args) {
		log.info("Main function is called");
		SpringApplication.run(CollateralManagementApplication.class, args);
		log.info("Main Funvtion ended");
	}

}
