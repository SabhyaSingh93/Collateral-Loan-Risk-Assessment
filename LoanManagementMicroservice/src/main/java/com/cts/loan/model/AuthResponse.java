package com.cts.loan.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * Pojo class for authentication response
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

	private String uId;
	private String name;
	private boolean isValid;

}
