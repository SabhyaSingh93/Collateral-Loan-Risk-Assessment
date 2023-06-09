package com.cts.clra.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.clra.model.CustomerLoan;

@Component
@FeignClient(name = "loan-service",url = "${loan.path}")
public interface LoanClient {
	
	@GetMapping(path = "/{loanId}")
	public ResponseEntity<CustomerLoan> getById(@PathVariable("loanId") int loanId,
			@RequestHeader(name = "Authorization") String token);
}
