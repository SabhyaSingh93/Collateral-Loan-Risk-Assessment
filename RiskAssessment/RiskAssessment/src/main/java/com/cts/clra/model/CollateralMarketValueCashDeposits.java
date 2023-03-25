package com.cts.clra.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *CollateralMarketValueCashDeposits pojo class for 
 *market value for cash deposite
 *
 *@Entity indicates Spring Data JPA that it is an
 *  entity class for application
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="collateral_market_value_cash_deposits")


public class CollateralMarketValueCashDeposits {
	/**
	 * @Id helps in defining the primary key
	 * @Column helps in defining the mapping table column
	 * 
	 */

	@Id
	@Column(name="market_id", length = 20)
	private int marketId;
	@Column(name="bank_name", length = 20)
	private String bankName;
	@Column(name="interest_rate", length = 20)
	private double interestRate;
	
}
