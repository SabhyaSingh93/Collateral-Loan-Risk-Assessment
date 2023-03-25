package com.cts.clra.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CollateralRisk class for risk 
 * based on  market value of cash and real estate
 * 
 *  @Entity indicates Spring Data JPA that it is an
 *  entity class for application
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "collateral_risk")

public class CollateralRisk {
	/**
	 * @Id helps in defining the primary key
	 * @Column helps in defining the mapping table column
	 * 
	 */

	@Id
	@Column(name = "risk_id")
	@GeneratedValue
	private int riskId;
	@Column(name = "name", length = 20)
	private String collateralType;
	@Column(name = "loan_id", length = 20)
	private int loanId;
	@Column(name = "collateral_id", length = 20)
	private int collateralId;
	@Column(name = "risk_percentage", length = 20)
	private double riskPercentage;
	@Column(name = "market_value", length = 20)
	private double marketValue;
	@Column(name = "sanctioned_loan", length = 20)
	private boolean sanctionedLoan;

}
