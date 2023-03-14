package com.payment.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.exception.BankAccountNotExsists;
import com.payment.exception.BankAlreadyAdded;
import com.payment.exception.NotAnyBankAddedYet;
import com.payment.exception.UserNotLogedinException;
import com.payment.model.BankAccount;
import com.payment.model.CurrentSessionUser;
import com.payment.model.Customer;
import com.payment.model.Wallet;
import com.payment.repository.BankAccountDao;
import com.payment.repository.CustomerDAO;
import com.payment.repository.LogInDAO;
import com.payment.repository.SessionDAO;

import java.util.*;

@Service
public class BanKAccountServiceImpl implements BankAccountService{

	@Autowired
	private BankAccountDao bankDao;
	
	@Autowired
	private SessionDAO sessionDao;
	
	@Autowired
	private CustomerDAO cDao;
	
	@Autowired
	private LogInDAO logInDAO;

	@Override
	public BankAccount addBank(BankAccount bankAccount, String uniqueId) throws UserNotLogedinException,BankAlreadyAdded {

		
		Optional<CurrentSessionUser> currentUser =  sessionDao.findByUuid(uniqueId);
		
		if(!currentUser.isPresent()) {
			throw new UserNotLogedinException("Please Login first");
		}
		
		Optional<Customer> customer =  cDao.findById(currentUser.get().getUserId());
		Wallet wallet = customer.get().getWallet();
		
		Optional<BankAccount> bankAc = bankDao.findById(bankAccount.getAccountNumber());
		
		if(bankAc.isPresent()) {
			throw new BankAlreadyAdded("Bank with "+bankAccount.getAccountNumber()+" this Account Nuber Already Exist");
		}
		
		System.out.println(wallet.getWalletId());
		bankAccount.setWalletId(wallet.getWalletId());
		return bankDao.save(bankAccount);

	}



	@Override
	public BankAccount removeBank(Integer accountNumber, String uniqueId)
			throws BankAccountNotExsists, UserNotLogedinException {
		Optional<CurrentSessionUser> currentUser =  sessionDao.findByUuid(uniqueId);
		
		if(!currentUser.isPresent()) {
			throw new UserNotLogedinException("Please Login first");
		}
		
		Optional<BankAccount> bankAccount = bankDao.findById(accountNumber);
		
		bankDao.delete(bankAccount.get());
		
		return bankAccount.get();
		
	}

	@Override
	public BankAccount viewBankAccountI(Integer accountNumber, String uniqueId)
			throws BankAccountNotExsists, UserNotLogedinException {
		
		Optional<CurrentSessionUser> currentUser =  sessionDao.findByUuid(uniqueId);
		
		if(!currentUser.isPresent()) {
			throw new UserNotLogedinException("Please Login first");
		}
		
		Optional<BankAccount> bankAccount = bankDao.findById(accountNumber);
		
		if(bankAccount.isPresent()) {
			return bankAccount.get();
		}else {
			throw new BankAccountNotExsists("Bank account is not existed with current account Number :" + accountNumber);
		}
		
		
	}

	@Override
	public BankAccount viewAllAccount(String uniqueId) throws UserNotLogedinException, NotAnyBankAddedYet, BankAccountNotExsists {
		Optional<CurrentSessionUser> currentUser =  sessionDao.findByUuid(uniqueId);
		
		if(!currentUser.isPresent()) {
			throw new UserNotLogedinException("Please Login first");
		}
		
		Optional<Customer> customer =  cDao.findById(currentUser.get().getUserId());
		Wallet wallet = customer.get().getWallet();
		
		BankAccount bankAccounts= bankDao.findByWalletId(wallet.getWalletId());
		
		if(bankAccounts!=null) {
			return bankAccounts;
		}else {
			throw new BankAccountNotExsists("Bank account is not existed in current user ");
		}
		
		
	}

}
