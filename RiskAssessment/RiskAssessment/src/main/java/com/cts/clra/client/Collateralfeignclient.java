package com.cts.clra.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.clra.model.CollateralCashDeposits;
import com.cts.clra.model.CollateralRealEstate;

@FeignClient(name = "collateral-service", url = "${collateral.path}")
public interface Collateralfeignclient {
	
	@GetMapping("/getCollaterals/realEstate/{loanId}")
	public ResponseEntity<CollateralRealEstate> getCollateralForRealEstate(
			@RequestHeader(name = "Authorization") String token, @PathVariable("loanId") int loanId);
	
	@GetMapping("/getCollaterals/cashDeposits/{loanId}")
	public ResponseEntity<CollateralCashDeposits> getCollateralForCashDeposit(
			@RequestHeader(name = "Authorization") String token, @PathVariable("loanId") int loanId);
}
