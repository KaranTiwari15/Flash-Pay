package com.payment.service;

import java.util.Set;

import com.payment.exception.BankAccountNotExsists;
import com.payment.exception.BankAlreadyAdded;
import com.payment.exception.NotAnyBankAddedYet;
import com.payment.exception.UserNotLogedinException;
import com.payment.model.BankAccount;

public interface BankAccountService {
	
	public BankAccount addBank(BankAccount bankAccount,String uniqueId) throws UserNotLogedinException,BankAlreadyAdded;

	public BankAccount removeBank(Integer accountNumber,String uniqueId) throws BankAccountNotExsists,UserNotLogedinException;
	
	public BankAccount viewBankAccountI(Integer accountNumber,String uniqueId) throws BankAccountNotExsists,UserNotLogedinException ;
	
	public BankAccount viewAllAccount(String uniqueId) throws UserNotLogedinException,NotAnyBankAddedYet, BankAccountNotExsists;
}
