package com.cts.clra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.clra.client.AuthClient;
import com.cts.clra.exception.CollateralNotFoundException;
import com.cts.clra.exception.DataNotPresentException;
import com.cts.clra.exception.InvalidAuthorizationException;
import com.cts.clra.model.AuthResponse;
import com.cts.clra.model.CollateralCashDeposits;
import com.cts.clra.model.CollateralRealEstate;
import com.cts.clra.model.Messages;
import com.cts.clra.service.CollateralService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Shiyam v
 *
 */
@RestController
@Slf4j
public class CollateralController {

//	private static final String AUTHSUCCESS = "Authorization Successful";
//	private static final String AUTHUNSUCCESS = "Authorization Unsuccessful";
//	private static final String INVALIDTOKEN = "Invalid Token";

	/**
     * Creates Automatically Objects
     */
	@Autowired
	AuthClient authclient;

	/**
     * Creates Automatically Objects
     */
	@Autowired
	private CollateralService collateralService;

	/**
	 * 
	 * @param token is used to verify the token based on token.
	 * @param loanId is used to get Amount based on loanId  
	 * @return the collateralRealEstate
	 * @throws InvalidAuthorizationException
	 * @throws DataNotPresentException 
	 * @throws CollateralNotFoundException 
	 */
	@GetMapping("/getCollaterals/realEstate/{loanId}")
	public ResponseEntity<CollateralRealEstate> getCollateralRealEstate(
			@RequestHeader(name = "Authorization") String token, @PathVariable int loanId)
			throws InvalidAuthorizationException,  CollateralNotFoundException {

		log.info("/getCollaterals/realEstate/{}", loanId + " is called");
		AuthResponse authResponse = authclient.verifyToken(token);

		
		if (authResponse.isValid()) {
			log.info(""+Messages.AUTHSUCCESS);
			CollateralRealEstate collateralRealEstate = collateralService.getCollateralRealEstate(loanId);

			log.info("/getCollaterals/realEstate/{}", loanId + " is ended");
			return new ResponseEntity<>(collateralRealEstate, HttpStatus.OK);
		} else {
			log.info(""+Messages.AUTHUNSUCCESS);
			log.info("/getCollaterals/realEstate/{}", loanId + " is ended");
			throw new InvalidAuthorizationException(""+Messages.INVALIDTOKEN);
		}

	}
	
	/**
	 * 
	 * @param token is used to verify the token based on token.
	 * @param realEstate amount saving the state
	 * @return store the realEstate values in the MySQL.
	 * @throws InvalidAuthorizationException
	 * @throws DataNotPresentException 
	 */
	@PostMapping(path = "/saveCollaterals/realEstate")
	public ResponseEntity<CollateralRealEstate> saveCollateralForRealEstate(@RequestHeader(name = "Authorization") String token,
			@RequestBody CollateralRealEstate realEstate) throws InvalidAuthorizationException, DataNotPresentException {
		
		log.info("/saveCollaterals/realEstate is called");
		AuthResponse authResponse = authclient.verifyToken(token);
		
		if (authResponse.isValid()) {
		CollateralRealEstate collateralRealEstate = collateralService.saveCollateralForRealEstate(realEstate);
		log.info("/saveCollaterals/realEstate is call ended");
		return new ResponseEntity<>(collateralRealEstate,HttpStatus.OK);
		}else {
			log.info(""+Messages.AUTHUNSUCCESS);
			log.info("/saveCollaterals/realEstate is call ended");
			throw new InvalidAuthorizationException(""+Messages.INVALIDTOKEN);
		}
	}

	/**
	 * 
	 * @param token is used to verify the token based on token.
	 * @param loanId is used to get Amount based on cashDeposits by loanId   
	 * @return the collateralCashDeposits
	 * @throws InvalidAuthorizationException
	 * @throws DataNotPresentException 
	 * @throws CollateralNotFoundException 
	 */
	@GetMapping("/getCollaterals/cashDeposits/{loanId}")
	public ResponseEntity<CollateralCashDeposits> getCollateralCashDeposits(@RequestHeader(name = "Authorization") String token,@PathVariable int loanId)
			throws InvalidAuthorizationException,  CollateralNotFoundException {
		
		log.info("/getCollaterals/cashDeposits/{}", + loanId + " is called");
		AuthResponse authResponse = authclient.verifyToken(token);
		
		if (authResponse.isValid()) {
			log.info(""+Messages.AUTHSUCCESS);
		CollateralCashDeposits collateralCashDeposits = collateralService.getCollateralCashDeposits(loanId);

		log.info("/getCollaterals/cashDeposits/{}", + loanId + " is ended");
		return new ResponseEntity<>(collateralCashDeposits, HttpStatus.OK);
		}else {
			log.info(""+Messages.AUTHUNSUCCESS);
			log.info("/getCollaterals/cashDeposits/{}", + loanId + " is ended");
			throw new InvalidAuthorizationException(""+Messages.INVALIDTOKEN);
		}
		
	}
 
	/**
	 * 
	 * @param token is used to verify the token based on token.
	 * @param cashDesposits amount saving the state
	 * @return store the CashDeposits values in the MySQL.
	 * @throws InvalidAuthorizationException
	 * @throws DataNotPresentException 
	 */
	@PostMapping(path = "/saveCollaterals/cashDeposits")
	public ResponseEntity<CollateralCashDeposits> saveCollateralCashDeposits(@RequestHeader(name = "Authorization") String token,
			@RequestBody CollateralCashDeposits cashDeposits) throws InvalidAuthorizationException, DataNotPresentException{
		
		log.info("/saveCollaterals/cashDeposits is called");
		AuthResponse authResponse = authclient.verifyToken(token);

		if (authResponse.isValid()) {
			log.info(""+Messages.AUTHSUCCESS);
		CollateralCashDeposits collateralCashDeposits = collateralService.saveCollateralCashDeposits(cashDeposits);
		log.info("/saveCollaterals/cashDeposits is call ended");
		return new ResponseEntity<>(collateralCashDeposits,HttpStatus.OK);
		}else {
			log.info(""+Messages.AUTHUNSUCCESS);
			log.info("/saveCollaterals/cashDeposits is call ended");
			throw new InvalidAuthorizationException(""+Messages.INVALIDTOKEN);
		}
	}

}
