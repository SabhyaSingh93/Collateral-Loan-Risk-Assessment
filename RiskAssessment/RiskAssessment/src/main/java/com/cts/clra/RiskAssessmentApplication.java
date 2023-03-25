package com.cts.clra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * this is the main class that 
 * will be used for running the microservice
 *
 */
@SpringBootApplication
@EnableFeignClients
public class RiskAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiskAssessmentApplication.class, args);
	}

}
