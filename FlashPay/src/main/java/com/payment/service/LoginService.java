package com.payment.service;

import com.payment.exception.LoginException;
import com.payment.model.LogIn;

public interface LoginService {
	
	public String logInAccount(LogIn loginData) throws LoginException;
	public String logOutFromAccount(String key) throws LoginException;
}
