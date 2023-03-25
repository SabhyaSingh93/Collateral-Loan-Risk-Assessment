package com.cts.loan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Pojo class for Cash Deposits Collaterals
 * @Data to implicitly have @Getter, @Setter, @ToString,
 * @EqualsAndHashCode and @RequiredArgsConstructor
 * annotations on the class
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollateralCashDeposits {
	
	
	private int collateralId;
	
	private int loanId; 
	
	private String ownerName;
	
	private String bankName;
	
	private long accountNumber;
	
	private double depositAmount;
	
	private double lockPeriod;

}
