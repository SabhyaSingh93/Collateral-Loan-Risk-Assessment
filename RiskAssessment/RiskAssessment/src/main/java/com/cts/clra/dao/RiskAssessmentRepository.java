package com.cts.clra.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.clra.model.CollateralRisk;

public interface RiskAssessmentRepository extends JpaRepository<CollateralRisk, Integer>{
	
	Optional<CollateralRisk> findByLoanIdAndCollateralType(int loanId, String collateralType);
}
