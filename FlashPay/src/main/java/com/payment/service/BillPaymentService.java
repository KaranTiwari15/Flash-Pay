package com.payment.service;

import java.util.Set;

import com.payment.exception.InsufficientBalanceException;
import com.payment.exception.UserNotLogedinException;
import com.payment.model.BillPayment;

public interface BillPaymentService {
	
	public BillPayment makeBillPayment(BillPayment billpayment,String uniqueId) throws InsufficientBalanceException, UserNotLogedinException;

	public Set<BillPayment> viewBillPayments(String uniqueId) throws UserNotLogedinException;
}
