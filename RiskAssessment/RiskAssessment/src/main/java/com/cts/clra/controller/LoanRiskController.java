package com.cts.clra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.clra.client.AuthClient;
import com.cts.clra.client.Collateralfeignclient;
import com.cts.clra.exception.CollateralNotFoundException;
import com.cts.clra.exception.CustomerLoanNotFoundException;
import com.cts.clra.exception.DataNotPresentException;
import com.cts.clra.exception.InvalidAuthorizationException;
import com.cts.clra.model.AuthResponse;
import com.cts.clra.model.CollateralRisk;
import com.cts.clra.model.Messages;
import com.cts.clra.service.RiskAssessmentService;

import lombok.extern.slf4j.Slf4j;

/**
 * LoanRiskController class used to handle 
 * request from view-risk page
 * 
 * @see riskAssessmentService
 */

@Slf4j
@RestController
public class LoanRiskController {

//	private static final String INVALIDTOKEN = "Invalid Token";
	
	@Autowired
	AuthClient authClient;

	@Autowired
	Collateralfeignclient collateralfeignclient;

	@Autowired
	RiskAssessmentService riskAssessmentService;
	
	/**
	 * getCollateralRisk method handle risk 
	 * for realEstate by loanId
	 *  http://localhost:8004/view-risk
	 * 
	 * @return
	 * @throws DataNotPresentException 
	 * @throws CustomerLoanNotFoundException 
	 * @throws CollateralNotFoundException 
	 */

	@GetMapping(path = "/getCollateralRisk/realEstate/{loanId}")
	public ResponseEntity<CollateralRisk> getcollatrolrisk(@RequestHeader(name = "Authorization") String token,
			@PathVariable("loanId") int loanId) throws InvalidAuthorizationException,  CollateralNotFoundException, CustomerLoanNotFoundException {
		log.info("/getCollateralRisk/realEstate/" + loanId + " is called");
		AuthResponse authResponse = authClient.verifyToken(token);
		/**
		 * verification of token is valid 
		 * for real estate with load id
		 * then it successfully accept aunthentication
		 * 
		 * if verification is invalid 
		 * then authentication failed 
		 * throw exception
		 * 
		 */
		if (authResponse.isValid()) {
			log.info("Authorization successful");
			return new ResponseEntity<>(riskAssessmentService.getRiskForRealEstate(token, loanId),
					HttpStatus.OK);
		} else {
			log.error("AuthClient: Authentication failed");
			throw new InvalidAuthorizationException(""+Messages.INVALIDTOKEN);
		}
	}
	
	/**
	 * getCollateralRisk method handle risk 
	 * for cash deposite  by loanId
	 *  http://localhost:8004/view-risk
	 * 
	 * @return
	 * @throws DataNotPresentException 
	 * @throws CustomerLoanNotFoundException 
	 * @throws CollateralNotFoundException 
	 */
	@GetMapping(path = "/getCollateralRisk/cashDeposits/{loanId}")
	public ResponseEntity<CollateralRisk> getcollatrolriskForCashDeposits(@RequestHeader(name = "Authorization") String token,
			@PathVariable("loanId") int loanId) throws InvalidAuthorizationException,  CollateralNotFoundException, CustomerLoanNotFoundException {

		log.info("/getCollateralRisk/cashDeposits/" + loanId + " is called");
		AuthResponse authResponse = authClient.verifyToken(token);
		/**
		 * verification of token is valid 
		 * for cash deposite with load id
		 * then it successfully accept aunthentication
		 * 
		 * if varification is invalid 
		 * then authentication failed 
		 * throw exception
		 * 
		 */
		
		if (authResponse.isValid()) {
			log.info("Authorization successful");
			return new ResponseEntity<>(riskAssessmentService.getRiskForCashDeposits(token, loanId),
					HttpStatus.OK);
		} else {
			log.error("AuthClient: Authentication failed");
			throw new InvalidAuthorizationException(""+Messages.INVALIDTOKEN);
		}
	}

}