package com.cts.clra.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.clra.exception.CollateralNotFoundException;
import com.cts.clra.exception.DataNotPresentException;
import com.cts.clra.model.CollateralCashDeposits;
import com.cts.clra.model.CollateralRealEstate;
import com.cts.clra.model.Messages;
import com.cts.clra.repository.CollateralCashDepositsRepository;
import com.cts.clra.repository.CollateralRealEstateRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CollateralServiceImpl implements CollateralService {

	/**
     * Creates Automatically Objects
     */
	@Autowired
	private CollateralRealEstateRepository collateralRealEstateRepository;

	/**
     * Creates Automatically Objects
     */
	@Autowired
	private CollateralCashDepositsRepository collateralCashDepositsRepository;

	/**
	 * This function is used to return collateralRealEstate values. In this function
	 * is based on {@link Optional} captaure the value and return the state of the
	 * id.
	 * 
	 * @return CollateralRealEstate
	 * @throws DataNotPresentException 
	 * @throws CollateralNotFoundException 
	 */
	@Override
	public CollateralRealEstate getCollateralRealEstate(int loanId) throws CollateralNotFoundException {
		log.info("getCollateralRealEstate in CollateralRealEstateService is called");
		Optional<CollateralRealEstate> findById = collateralRealEstateRepository.findById(loanId);
		CollateralRealEstate collateralRealEstate = findById.get();
		if(collateralRealEstate==null){
			throw new CollateralNotFoundException(""+Messages.NOIDFOUND);
		}
		log.info("getCollateralRealEstate in CollateralRealEstateService is call ended");
		return collateralRealEstate;
	}

	/**
	 * This function is used to save the colleteral values of the realEstate.
	 * This service function call the main function by the references.
	 * @return CollateralRealEstate
	 * @throws DataNotPresentException 
	 */
	@Override
	public CollateralRealEstate saveCollateralForRealEstate(CollateralRealEstate realEstate) throws DataNotPresentException {
		log.info("saveCollateralRealEstate in CollateralRealEstateService is called");
		CollateralRealEstate collateralRealEstate = collateralRealEstateRepository.save(realEstate);
		if(collateralRealEstate==null){
			throw new DataNotPresentException(""+Messages.NODATA);
		}
		log.info("saveCollateralRealEstate in CollateralRealEstateService is call ended");
		return collateralRealEstate;
	}

	/**
	 * This function is used to return CollateralCashDeposits values.
	 * In this function is based on  Optional captaure the value and 
	 * return the state of the id.
	 * 
	 * @return collateralCashDeposits
	 * @throws DataNotPresentException 
	 * @throws CollateralNotFoundException 
	 */
	@Override
	public CollateralCashDeposits getCollateralCashDeposits(int loanId) throws  CollateralNotFoundException {
		log.info("getCollateralCashDeposits in CollateralCashDepositsService is calling start");
		Optional<CollateralCashDeposits> findById = collateralCashDepositsRepository.findById(loanId);
		CollateralCashDeposits collateralCashDeposits = findById.get();
		if(collateralCashDeposits==null){
			throw new CollateralNotFoundException(""+Messages.NOIDFOUND);
		}
		log.info("getCollateralCashDeposits in CollateralCashDepositsService is calling end");
		return collateralCashDeposits;
	}

	/**
	 * This function is used to save the colleteral values of the CashDeposits.
	 * This service function call the main function by the references.
	 * @return collateralCashDeposits
	 * @throws DataNotPresentException 
	 */
	@Override
	public CollateralCashDeposits saveCollateralCashDeposits(CollateralCashDeposits cashDeposits) throws DataNotPresentException {
		log.info("saveCollateralCashDeposits in CollateralCashDepositsService is called");
		CollateralCashDeposits collateralCashDeposits = collateralCashDepositsRepository.save(cashDeposits);
		if(collateralCashDeposits==null){
			throw new DataNotPresentException(""+Messages.NODATA);
		}
		log.info("saveCollateralCashDeposits in CollateralCashDepositsService is call ended");
		return collateralCashDeposits;
	}

}
