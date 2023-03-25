package com.cts.clra.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

/**
 *  CustomerLoan class to 
 *  provide loan by verifying based on different factor
 * @author 
 *
 */
public class CustomerLoan {

	private int loanId;

	private int loanProductId;
	private int customerId;

	private double loanPrincipal;

	private int tenureYear;

	private double interest;

	private int emi;

	private int collateralId;

}
