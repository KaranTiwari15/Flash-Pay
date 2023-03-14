package com.payment.service;

import com.payment.exception.LoginException;
import com.payment.model.*;

public interface CustomerService {
	
	public Customer createNewSignUp(Customer signUp) throws LoginException;;
	
	public Customer updateSignUpDetails(Customer signUp,String key) throws LoginException;
}
