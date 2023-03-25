package com.cts.auth.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.auth.model.Admin;
import com.cts.auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository urepo;
	@Autowired
	private JwtUtil jwtutil;

	@Override
	public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		log.debug("Start : {}", "loadUserByUsername");
		Admin custuser = urepo.findById(uid).orElse(null);

		log.debug("End : {}", "loadUserByUsername");
		return new User(custuser.getUserid(), custuser.getUpassword(), new ArrayList<>());
	}
	
	public String getUserName(String token) {
		return urepo.findById(jwtutil.extractUsername(token)).orElse(null).getUname();
	}

}
