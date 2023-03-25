package com.cts.clra.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.clra.client.Collateralfeignclient;
import com.cts.clra.client.LoanClient;
import com.cts.clra.dao.CollateralMarketValueCashDepositsRepository;
import com.cts.clra.dao.CollateralMarketValueRealEstateRepository;
import com.cts.clra.dao.RiskAssessmentRepository;
import com.cts.clra.exception.CollateralNotFoundException;
import com.cts.clra.exception.CustomerLoanNotFoundException;
import com.cts.clra.exception.DataNotPresentException;
import com.cts.clra.model.CollateralCashDeposits;
import com.cts.clra.model.CollateralMarketValueCashDeposits;
import com.cts.clra.model.CollateralMarketValueRealEstate;
import com.cts.clra.model.CollateralRealEstate;
import com.cts.clra.model.CollateralRisk;
import com.cts.clra.model.CustomerLoan;
import com.cts.clra.model.Messages;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

/**
 * RiskAssessmentServiceImpl is the implementation 
 * of Service class
 *
 */

public class RiskAssessmentServiceImpl implements RiskAssessmentService {

	@Autowired
	private Collateralfeignclient collateralfeignclient;
	@Autowired
	private LoanClient loanClient;
	
	/** RiskAssessmentRepository is auto wired 
	 * with  riskAssessmentRepository object
	 * 
	 * CollateralMarketValueRealEstateRepository is auto wired
	 * with realEstateRepository object
	 *
	 * CollateralMarketValueCashDepositsRepository is auto wired 
	 *  with cashDepositsRepository object
	 *  
	 */
	@Autowired
	private RiskAssessmentRepository riskAssessmentRepository;

	@Autowired
	private CollateralMarketValueRealEstateRepository realEstateRepository;

	@Autowired
	private CollateralMarketValueCashDepositsRepository cashDepositsRepository;
	
	/**
	 * getRiskForRealEstate method return the 
	 * market value of collateral Real Estate 
	 * and calculate the risk percentage and SanctionedLoan  
	 * according  to market value 
	 * 
	 * 
	 * @param marketvalue
	 * @param current value
	 * @return
	 * @throws DataNotPresentException 
	 * @throws CollateralNotFoundException 
	 * @throws CustomerLoanNotFoundException 
	 */

	public CollateralRisk getRiskForRealEstate(String token, int loanId) throws  CollateralNotFoundException, CustomerLoanNotFoundException {
		
		CollateralRealEstate collateralRealEstate;

		CustomerLoan loan;
		double currentValue = 0;
		double marketValue = 0;
		CollateralMarketValueRealEstate marketValueRealEstate;

		log.info("Collaterals-Management Microservice is called");
		collateralRealEstate = collateralfeignclient.getCollateralForRealEstate(token, loanId).getBody();
		if(collateralRealEstate==null) {
			throw new CollateralNotFoundException(""+Messages.NOIDFOUND);
		}
		log.info("Loan-Management Microservice is called");
		loan = loanClient.getById(loanId, token).getBody();
		if(loan==null)
			throw new CustomerLoanNotFoundException(""+Messages.NOLOANINFO);
		else {
			currentValue = calculateCurrentValue(loan.getLoanPrincipal(), loan.getInterest(), loan.getTenureYear());
		}

		if (collateralRealEstate != null) {
			marketValueRealEstate = realEstateRepository.findByCityAndState(collateralRealEstate.getCity(),
					collateralRealEstate.getState());
			marketValue = marketValueRealEstate.getRatePerSqft() * collateralRealEstate.getAreaInFt();
		}

		double riskPercentage = calculateRiskPercentage(marketValue, currentValue);
		Optional<CollateralRisk> optionalCollateralRisk = riskAssessmentRepository.findByLoanIdAndCollateralType(loanId,
				"Real Estate");
		log.info("Optional Collateral"+optionalCollateralRisk);
		if (optionalCollateralRisk.isPresent()) {
			log.info("CollateralRisk1:{}",optionalCollateralRisk.isPresent());
			return optionalCollateralRisk.get();
		}else {
			log.info("this is ELSE");
			CollateralRisk newCollateralRisk = new CollateralRisk();
			newCollateralRisk.setCollateralType("Real Estate");
			newCollateralRisk.setLoanId(loanId);
			newCollateralRisk.setRiskPercentage(riskPercentage);
			newCollateralRisk.setMarketValue(marketValue);
			newCollateralRisk.setCollateralId(collateralRealEstate.getCollateralId());
			newCollateralRisk.setSanctionedLoan(isLoanSanctioned(riskPercentage));
			log.info("CollateralRisk:{}",newCollateralRisk);
			return riskAssessmentRepository.save(newCollateralRisk);
		}	

	}

	/**
	 * getRiskForCashDeposits method return the 
	 * market value of cash deposite 
	 * and calculate the risk percentage and SanctionedLoan  
	 * according  to market value 
	 * 
	 * 
	 * @param marketvalue
	 * @param current value
	 * @param Interest
	 * @param TenureYear
	 * @return
	 * @throws DataNotPresentException 
	 * @throws CollateralNotFoundException 
	 * @throws CustomerLoanNotFoundException 
	 */
	public CollateralRisk getRiskForCashDeposits(String token, int loanId) throws  CollateralNotFoundException, CustomerLoanNotFoundException {

		CollateralCashDeposits collateralCashDeposits;

		CustomerLoan loan;
		double currentValue = 0;
		double marketValue = 0;
		CollateralMarketValueCashDeposits marketValueCashDeposits;

		log.info("Collaterals-Management Microservice is called");
		collateralCashDeposits = collateralfeignclient.getCollateralForCashDeposit(token, loanId).getBody();
		if(collateralCashDeposits==null) {
			throw new CollateralNotFoundException(""+Messages.NOIDFOUND);
		}
		
		log.info("Loan-Management Microservice is called");

		loan = loanClient.getById(loanId, token).getBody();
		log.info("Loan-Management{}:", loan);
		if(loan==null)
			throw new CustomerLoanNotFoundException(""+Messages.NOLOANINFO);
		else {
			currentValue = calculateCurrentValue(loan.getLoanPrincipal(), loan.getInterest(), loan.getTenureYear());
		}

		if (collateralCashDeposits != null) {
			marketValueCashDeposits = cashDepositsRepository.findByBankName(collateralCashDeposits.getBankName());
			marketValue = collateralCashDeposits.getDepositAmount() + ((marketValueCashDeposits.getInterestRate()
					* collateralCashDeposits.getDepositAmount() * collateralCashDeposits.getLockPeriod()) / 100);
		}

		double riskPercentage = calculateRiskPercentage(marketValue, currentValue);
		Optional<CollateralRisk> optionalCollateralRisk = riskAssessmentRepository.findByLoanIdAndCollateralType(loanId,
				"Cash Deposits");
		if (optionalCollateralRisk.isPresent()) {

			return optionalCollateralRisk.get();
		}

		CollateralRisk newCollateralRiskCashDeposits = new CollateralRisk();
		newCollateralRiskCashDeposits.setCollateralType("Cash Deposits");
		newCollateralRiskCashDeposits.setLoanId(loanId);
		newCollateralRiskCashDeposits.setRiskPercentage(riskPercentage);
		newCollateralRiskCashDeposits.setMarketValue(marketValue);
		newCollateralRiskCashDeposits.setCollateralId(collateralCashDeposits.getCollateralId());
		newCollateralRiskCashDeposits.setSanctionedLoan(isLoanSanctioned(riskPercentage));
		log.info("CollateralRisk:",newCollateralRiskCashDeposits);
		return riskAssessmentRepository.save(newCollateralRiskCashDeposits);

	}
	/**
	 * calculating risk percentage
	 * @param marketValue
	 * @param currentValue
	 * @return
	 */

	public double calculateRiskPercentage(double marketValue, double currentValue) {

		double riskFactor = (marketValue / currentValue);

		if (riskFactor >= 1.5) {
			return 0;
		} else {
			if (riskFactor > 0.5 && riskFactor < 1.5) {
				return (150 - (riskFactor * 100));
			} else {
				return 100;
			}
		}
	}
	
	/**
	 * calculate market current value
	 * @param loanPrincipal
	 * @param interest
	 * @param tenureYear
	 * @return
	 */

	public double calculateCurrentValue(double loanPrincipal, double interest, int tenureYear) {

		return loanPrincipal + (loanPrincipal * interest * tenureYear)/100;

	}
	
	
	/**
	 * find sanctioned loan
	 * @param riskPercentage
	 * @return
	 */
	public boolean isLoanSanctioned(double riskPercentage) {
		if(riskPercentage >= 0 && riskPercentage <= 50) {
			return true;
		}else {
			return false;
		}
	}
}