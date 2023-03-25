package com.cts.clra.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

/**
 * CollateralCashDeposits pojo class for
 * cash deposite
 * 
 */
		
public class CollateralCashDeposits {

	private int collateralId;
	private int loanId;

	private String ownerName;
	private String bankName;
	private long accountNumber;
	private double depositAmount;
	private double lockPeriod;
}
