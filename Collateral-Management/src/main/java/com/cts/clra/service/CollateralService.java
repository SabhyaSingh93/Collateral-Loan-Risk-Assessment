package com.cts.clra.service;

import com.cts.clra.exception.CollateralNotFoundException;
import com.cts.clra.exception.DataNotPresentException;
import com.cts.clra.model.CollateralCashDeposits;
import com.cts.clra.model.CollateralRealEstate;


public interface CollateralService {

	CollateralRealEstate getCollateralRealEstate(int loanId) throws  CollateralNotFoundException;

	 
	CollateralRealEstate saveCollateralForRealEstate(CollateralRealEstate realEstate) throws DataNotPresentException;


	CollateralCashDeposits getCollateralCashDeposits(int loanId) throws CollateralNotFoundException;


	CollateralCashDeposits saveCollateralCashDeposits(CollateralCashDeposits cashDeposits) throws DataNotPresentException;

	
	
	
	
}
