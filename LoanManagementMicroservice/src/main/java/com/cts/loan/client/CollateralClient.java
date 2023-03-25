package com.cts.loan.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.loan.model.CollateralCashDeposits;
import com.cts.loan.model.CollateralRealEstate;

/**
 * 
 *  This is a Feign client responsible for saving the collaterals if
 *         it is Real Estate or Cash Deposits
 * 
 *
 */
@FeignClient(url = "${collateral.path}", name = "collateral-service")

public interface CollateralClient {

	/**
	 * 
	 * @param token
	 * @param realEstates
	 * saves the collaterals for real estate
	 */
	@PostMapping(path = "/saveCollaterals/realEstate")
	public ResponseEntity<CollateralRealEstate> saveCollateralsForRealEstate(
			@RequestHeader(name = "Authorization") String token, @RequestBody CollateralRealEstate realEstates);

	/**
	 * 
	 * @param token
	 * @param cashDeposits
	 * saves the collaterals for Cash deposits
	 */
	@PostMapping(path = "/saveCollaterals/cashDeposits")
	public ResponseEntity<CollateralCashDeposits> saveCollateralsForCashDeposits(
			@RequestHeader(name = "Authorization") String token, @RequestBody CollateralCashDeposits cashDeposits);
}
