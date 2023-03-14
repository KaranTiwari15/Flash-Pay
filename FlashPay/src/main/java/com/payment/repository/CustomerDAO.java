package com.payment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payment.model.Customer;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Integer>{
	
	public Optional<Customer> findByUserName(String userName);
	
	public Optional<Customer>findByMobileNo(String mobileNo);
}


