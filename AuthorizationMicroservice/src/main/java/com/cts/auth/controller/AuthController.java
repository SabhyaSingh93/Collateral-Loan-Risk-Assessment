package com.cts.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.auth.Exception.InvalidAuthorizationException;
import com.cts.auth.model.Admin;
import com.cts.auth.model.AuthResponse;
import com.cts.auth.model.Messages;

import com.cts.auth.service.CustomerDetailsService;
import com.cts.auth.service.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AuthController {

//	private final static String LOGIN = "login";
	@Autowired
	private JwtUtil jwtutil;
	@Autowired
	private CustomerDetailsService custdetailservice;

	


	@PostMapping(value = "/login")
	public ResponseEntity<Admin> login(@RequestBody Admin userlogincredentials) throws InvalidAuthorizationException {
		log.debug("Start : {}", Messages.LOGIN);
		final UserDetails userdetails = custdetailservice.loadUserByUsername(userlogincredentials.getUserid());
		if (userdetails.getPassword().equals(userlogincredentials.getUpassword())) {
			log.debug("End : {}", Messages.LOGIN);
			return new ResponseEntity<Admin>(
					new Admin(userlogincredentials.getUserid(), null, null, jwtutil.generateToken(userdetails)),
					HttpStatus.OK);
		} else {
			log.debug("Access Denied : {}", Messages.LOGIN);
		throw new InvalidAuthorizationException(""+Messages.INVALIDTOKEN);	
		}
	}

	@GetMapping(value = "/validate")
	public ResponseEntity<AuthResponse> getValidity(@RequestHeader("Authorization") String token) {
		log.debug("Start : {}", "getValidity");
		token = token.substring(7);
		AuthResponse res = new AuthResponse();
		if (jwtutil.validateToken(token)) {
			res.setUid(jwtutil.extractUsername(token));
			res.setValid(true);
			res.setName(custdetailservice.getUserName(token));

		} else
			res.setValid(false);

		log.debug("End : {}", "getValidity");
		return new ResponseEntity<>(res, HttpStatus.OK);
	}}//tm system pe nhi ho kya??hai..ok samjhne ki koshish kro...yr sab mix ho rha ...yha pr kya dekhna h//yr bs hme ye dikhao pure authrization me feign kaha use hua h
// lakin portal wale authfeign h na...mtlb