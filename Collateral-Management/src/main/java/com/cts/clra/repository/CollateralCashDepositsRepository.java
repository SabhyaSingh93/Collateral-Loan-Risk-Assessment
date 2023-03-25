package com.cts.clra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.clra.model.CollateralCashDeposits;

@Repository
public interface CollateralCashDepositsRepository extends JpaRepository<CollateralCashDeposits, Integer> {

}
