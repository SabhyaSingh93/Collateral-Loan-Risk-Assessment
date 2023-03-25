package com.cts.clra.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * CollateralMarketValueRealEstate pojo class for 
 * market value of real estate
 * 
 *  @Entity indicates Spring Data JPA that it is an
 *  entity class for application
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="collateral_market_value_real_estate")


public class CollateralMarketValueRealEstate {
	
	/**
	 * 
	 * @Id helps in defining the primary key
	 * @Column helps in defining the mapping table column
	 * 
	 */

	@Id
	@Column(name="market_id",length=20)
	private int marketId;
	@Column(name="city",length=20)
	private String city;
	@Column(name="state",length=20)
	private String state;
	@Column(name="rate_per_sqft",length=20)
	private double ratePerSqft;
	
}
