package com.payment.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import com.payment.exception.CustomerNotException;
import com.payment.exception.TransactionNotFoundException;
import com.payment.exception.UserNotLogedinException;
import com.payment.model.Transaction;
import com.payment.model.TransactionType;
import com.payment.model.Wallet;

public interface TransactionService {
	
	public List<Transaction> viewAlltransaction(String  uniqueId)throws UserNotLogedinException, TransactionNotFoundException ;
	
	public List<Transaction> viewTranscationByDate(String from, String to , String uniqueId)  throws UserNotLogedinException,TransactionNotFoundException ;
		
	public List<Transaction> viewAllTransactionbyTransactionType(String uniqueId,TransactionType type) throws UserNotLogedinException, TransactionNotFoundException;


}
