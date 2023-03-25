package com.cts.clra.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
@Entity(name = "collateral_cash_deposits")
public class CollateralCashDeposits {

	@Id
	@GeneratedValue
	@Column(name = "collateral_id")
	private int collateralId;
	@Column(name = "loan_id")
	private int loanId;
	@Column(name = "owner_name")
	private String ownerName;
	@Column(name = "bank_name")
	private String bankName;
	@Column(name = "account_number")
	private long accountNumber;
	@Column(name = "deposit_amount")
	private double depositAmount;
	@Column(name = "lock_period")
	private double lockPeriod;

}
