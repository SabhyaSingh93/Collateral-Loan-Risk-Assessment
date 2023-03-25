package com.cts.clra.service;

import com.cts.clra.exception.CollateralNotFoundException;
import com.cts.clra.exception.CustomerLoanNotFoundException;
import com.cts.clra.exception.DataNotPresentException;
import com.cts.clra.model.CollateralRisk;

public interface RiskAssessmentService {

	CollateralRisk getRiskForRealEstate(String collateralType, int loanId) throws  CollateralNotFoundException, CustomerLoanNotFoundException;

	CollateralRisk getRiskForCashDeposits(String token, int loanId) throws  CollateralNotFoundException, CustomerLoanNotFoundException;

}