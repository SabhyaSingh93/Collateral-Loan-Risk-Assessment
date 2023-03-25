package com.cts.loan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.loan.client.AuthClient;
import com.cts.loan.client.CollateralClient;
import com.cts.loan.exception.CustomerLoanNotFoundException;
import com.cts.loan.exception.DataNotPresentException;
import com.cts.loan.exception.InvalidAuthorizationException;
import com.cts.loan.model.AuthResponse;
import com.cts.loan.model.CollateralCashDeposits;
import com.cts.loan.model.CollateralRealEstate;
import com.cts.loan.model.CustomerLoan;
import com.cts.loan.model.Messages;
import com.cts.loan.service.LoanService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This is Loan Management Controller class annotated
 *         with @RestController
 * @Slf4j to generate getters and setters for all the model classes
 */
@RestController
@Slf4j
public class LoanManagementController {

//	private static final String INVALIDTOKEN = "Invalid Token";
//	private static final String AUTHENTICATIONSUCESS = "Authentication validated";
//	private static final String AUTHENTICATIONFAIL = "Authentication Invalidated";

	/**
	 * loanService reference of LoanService it is autowired
	 */
	@Autowired
	private LoanService loanService;

	/**
	 * authClient reference of AuthClient it is autowired
	 */
	@Autowired
	private AuthClient authClient;

	/**
	 * collateralClient reference of CollateralCLient it is autowired
	 */
	@Autowired
	private CollateralClient collateralClient;

	/**
	 * 
	 * @param loanId
	 * @param token
	 * this method get the loan details on the basis of customer loan Id
	 * @throws InvalidAuthorizationException
	 * @throws DataNotPresentException 
	 * @throws CustomerLoanNotFoundException 
	 */
	@GetMapping(path = "/{loanId}")
	public ResponseEntity<CustomerLoan> getById(@PathVariable("loanId") int loanId,
			@RequestHeader(name = "Authorization") String token) throws InvalidAuthorizationException,  CustomerLoanNotFoundException {

		log.info("AuthClient: VerifyToken method called");
		AuthResponse authResponse;

		authResponse = authClient.verifyToken(token);

		if (authResponse.isValid()) {
			log.info(""+Messages.AUTHSUCCESS);
	

			log.info("customerLoanService method called");
			return new ResponseEntity<>(loanService.findByCustomerLoanId(loanId), HttpStatus.OK);
		} else {
			log.error(""+Messages.AUTHUNSUCCESS);
			
			throw new InvalidAuthorizationException(""+Messages.INVALIDTOKEN);
		}

	}

	/**
	 * 
	 * @param token
	 * This method get the loan details of all the customers
	 * @throws InvalidAuthorizationException
	 * @throws DataNotPresentException 
	 */
	@GetMapping(path = "/getAllCustomerLoan")
	public ResponseEntity<List<CustomerLoan>> getallCustomerLoan(@RequestHeader(name = "Authorization") String token)
			throws InvalidAuthorizationException, DataNotPresentException {

		log.info("AuthClient: VerifyToken method called");
		AuthResponse authResponse;
		authResponse = authClient.verifyToken(token);

		if (authResponse.isValid()) {
			log.info(""+Messages.AUTHSUCCESS);
			log.info("AuthClient:Authentication validated");
			return new ResponseEntity<>(loanService.getAllCustomerLoan(), HttpStatus.OK);
		} else {
			log.error(""+Messages.AUTHUNSUCCESS);
			throw new InvalidAuthorizationException(""+Messages.INVALIDTOKEN);
		}

	}

	/**
	 * 
	 * @param token
	 * @param customerLoan 
	 * this method save the loan details of a customer
	 * @throws InvalidAuthorizationException
	 * @throws DataNotPresentException 
	 */
	@PostMapping(path = "/saveCustomerLoan")
	public ResponseEntity<CustomerLoan> saveCustomerLoan(@RequestHeader(name = "Authorization") String token,
			@RequestBody CustomerLoan customerLoan) throws InvalidAuthorizationException, DataNotPresentException {
		log.info("AuthClient: VerifyToken method called");
		AuthResponse authResponse;
		authResponse = authClient.verifyToken(token);

		if (authResponse.isValid()) {
			log.info(""+Messages.AUTHSUCCESS);
			log.info("AuthClient: VerifyToken method ended");
			return new ResponseEntity<>(loanService.saveCustomerLoan(customerLoan), HttpStatus.OK);
		} else {
			log.error(""+Messages.AUTHUNSUCCESS);
			throw new InvalidAuthorizationException(""+Messages.INVALIDTOKEN);
		}
	}

	/**
	 * 
	 * @param token
	 * @param loanId
	 * @param collateralRealEstate 
	 * this method saves collaterals for Real Estate on
	 * the basis of Loan Id
	 * @throws InvalidAuthorizationException
	 */
	@PostMapping(path = "/saveCustomerLoan/realEstate/{loanId}")
	public ResponseEntity<CollateralRealEstate> saveCollateralRealEstate(
			@RequestHeader(name = "Authorization") String token, @PathVariable("loanId") int loanId,
			@RequestBody CollateralRealEstate collateralRealEstate) throws InvalidAuthorizationException {
		log.info("AuthClient: VerifyToken method called");
		AuthResponse authResponse;
		authResponse = authClient.verifyToken(token);

		if (authResponse.isValid()) {
			collateralRealEstate.setLoanId(loanId);
			log.info(""+Messages.AUTHSUCCESS);
			log.info("Collaterals-Management Microservice is called");
			return new ResponseEntity<>(
					collateralClient.saveCollateralsForRealEstate(token, collateralRealEstate).getBody(),
					HttpStatus.OK);
		} else {
			log.error("AuthClient: Authentication failed");
			throw new InvalidAuthorizationException(""+Messages.INVALIDTOKEN);
		}

	}

	/**
	 * 
	 * @param token
	 * @param loanId
	 * @param collateralCashDeposits
	 * This method saves collaterals for Cash deposits
	 *  on the basis of loan Id
	 * @throws InvalidAuthorizationException
	 */
	@PostMapping(path = "/saveCustomerLoan/cashDeposit/{loanId}")
	public ResponseEntity<CollateralCashDeposits> saveCollateralCashDeposit(
			@RequestHeader(name = "Authorization") String token, @PathVariable("loanId") int loanId,
			@RequestBody CollateralCashDeposits collateralCashDeposits) throws InvalidAuthorizationException {
		log.info("AuthClient: VerifyToken method called");
		AuthResponse authResponse;
		authResponse = authClient.verifyToken(token);

		log.info("authresponse:{}", authResponse);

		if (authResponse.isValid()) {
			collateralCashDeposits.setLoanId(loanId);
			log.info(""+Messages.AUTHSUCCESS);
			log.info("Collaterals-Management Microservice is called");
			return new ResponseEntity<>(
					collateralClient.saveCollateralsForCashDeposits(token, collateralCashDeposits).getBody(),
					HttpStatus.OK);

		} else {
			log.error("AuthClient: Authentication failed");
			throw new InvalidAuthorizationException(""+Messages.INVALIDTOKEN);
		}

	}

}