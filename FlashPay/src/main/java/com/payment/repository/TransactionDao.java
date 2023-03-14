package com.payment.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.payment.model.Transaction;
import com.payment.model.TransactionType;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer>{
	
	public List<Transaction> findByWalletId(Integer walletId);
	
	public List<Transaction> getTransactionByTransactionType(TransactionType type);

	public List<Transaction> findByTransactionDate(LocalDate date);
	

}
