package com.payment.service;

import com.payment.exception.LoginException;
import com.payment.model.CurrentSessionUser;
import com.payment.model.Customer;

public interface CurrentUserSessionService {
	public CurrentSessionUser getCurrentUserSession(String key) throws LoginException;;
	public Integer getCurrentUserSessionId(String key) throws LoginException;;
	
	public Customer getSignUpDetails(String key) throws LoginException;;
}
