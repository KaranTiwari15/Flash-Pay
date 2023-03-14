package com.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.payment.model.Customer;
import com.payment.model.LogIn;

@Repository
public interface LogInDAO extends JpaRepository<LogIn, Integer>{
	
	
}
