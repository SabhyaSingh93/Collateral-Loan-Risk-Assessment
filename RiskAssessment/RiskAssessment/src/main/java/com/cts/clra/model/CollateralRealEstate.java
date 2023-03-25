package com.cts.clra.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CollateralRealEstat class for 
 * property of real estate
 * 
 * @Entity indicates Spring Data JPA that it is an
 *  entity class for application
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class CollateralRealEstate {

	private int collateralId;

	private int loanId;
	private String ownerName;
	private String address;
	private String city;
	private String state;
	private int areaInFt;
}
