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
@Entity(name = "collateral_real_state")
public class CollateralRealEstate {

	@Id
	@GeneratedValue
	@Column(name = "collateral_id")
	private int collateralId;
	@Column(name = "loan_id")
	private int loanId;
	@Column(name = "owner_name")
	private String ownerName;
	@Column(name = "address")
	private String address;
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "area_in_ft")
	private int areaInFt;

}
