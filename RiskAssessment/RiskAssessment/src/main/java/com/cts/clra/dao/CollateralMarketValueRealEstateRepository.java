package com.cts.clra.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.clra.model.CollateralMarketValueRealEstate;


public interface CollateralMarketValueRealEstateRepository extends JpaRepository<CollateralMarketValueRealEstate, Integer> {
	
	CollateralMarketValueRealEstate findByCityAndState(String city, String state);
}