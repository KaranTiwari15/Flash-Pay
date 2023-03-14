package com.payment.service;

import java.math.BigDecimal;

import java.util.*;

import javax.naming.InsufficientResourcesException;

import com.payment.exception.BeneficiaryDetailException;
import com.payment.exception.CustomerNotException;
import com.payment.exception.InsufficientBalanceException;
import com.payment.exception.LoginException;
import com.payment.model.BeneficiaryDetail;
import com.payment.model.Customer;
import com.payment.model.Transaction;
import com.payment.model.Wallet;

public interface WalletService {

	public  Double showBalance(String mobileNo) throws CustomerNotException, LoginException;
	
	public Transaction fundTransfer(String sourceMoblieNo,String targetMobileNo,Double amout,String uniqueId) throws CustomerNotException, BeneficiaryDetailException, LoginException,InsufficientBalanceException;
	
	public Transaction depositeAmount(String uniqueId,Double amount) throws CustomerNotException, LoginException, InsufficientResourcesException, InsufficientBalanceException;
	
	public List<BeneficiaryDetail> getList(String uniqueId) throws CustomerNotException, LoginException, BeneficiaryDetailException;
	
	public Customer addMoney(String uniqueId, Double amount) throws Exception;
	
}
