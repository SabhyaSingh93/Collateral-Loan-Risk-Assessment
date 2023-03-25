package com.cts.clra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.clra.model.CollateralRealEstate;

@Repository
public interface CollateralRealEstateRepository extends JpaRepository<CollateralRealEstate, Integer>{

}
