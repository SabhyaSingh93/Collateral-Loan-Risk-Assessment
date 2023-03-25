package com.cts.clra.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.clra.model.CollateralMarketValueCashDeposits;

public interface CollateralMarketValueCashDepositsRepository extends JpaRepository<CollateralMarketValueCashDeposits, Integer> {

	CollateralMarketValueCashDeposits findByBankName(String bankName);
}
