package com.payment.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payment.model.BankAccount;

@Repository
public interface BankAccountDao extends JpaRepository<BankAccount, Integer>{

	public BankAccount findByWalletId(Integer walletId);
    
}
