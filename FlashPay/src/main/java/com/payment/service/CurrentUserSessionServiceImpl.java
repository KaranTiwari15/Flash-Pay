package com.payment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.exception.LoginException;
import com.payment.model.CurrentSessionUser;
import com.payment.model.Customer;
import com.payment.repository.CustomerDAO;
import com.payment.repository.SessionDAO;

@Service
public class CurrentUserSessionServiceImpl implements CurrentUserSessionService{
	
	@Autowired
	private SessionDAO sessionDAO;
	
	@Autowired
	private CustomerDAO signUpDAO;
	
	@Override
	public CurrentSessionUser getCurrentUserSession(String key) throws LoginException {
		Optional<CurrentSessionUser> currentSessionuser = sessionDAO.findByUuid(key);
		
		if(currentSessionuser.isPresent()) {
			return currentSessionuser.get();
		}else {
			throw new LoginException("UnAuthorized!!!");
		}
	}

	@Override
	public Integer getCurrentUserSessionId(String key) throws LoginException {
		Optional<CurrentSessionUser> currentUser = sessionDAO.findByUuid(key);
		if(!currentUser.isPresent())
		{
			throw new LoginException("UnAuthorized!!!");
		}
		return currentUser.get().getId();
	}

	@Override
	public Customer getSignUpDetails(String key) throws LoginException {
		Optional<CurrentSessionUser> currentUser = sessionDAO.findByUuid(key);
		if(!currentUser.isPresent())
		{
			return null;
		}
		Integer SignUpUserId = currentUser.get().getUserId();
		System.out.println(SignUpUserId );
		
		return (signUpDAO.findById(SignUpUserId)).get();
	}

}
