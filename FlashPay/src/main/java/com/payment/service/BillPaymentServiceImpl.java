package com.payment.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.exception.InsufficientBalanceException;
import com.payment.exception.UserNotLogedinException;
import com.payment.model.BillPayment;
import com.payment.model.CurrentSessionUser;
import com.payment.model.Customer;
import com.payment.model.Transaction;
import com.payment.model.Wallet;
import com.payment.repository.BankAccountDao;
import com.payment.repository.BillPaymentDao;
import com.payment.repository.CustomerDAO;
import com.payment.repository.SessionDAO;
import com.payment.repository.TransactionDao;
import com.payment.repository.WalletDao;

@Service
public class BillPaymentServiceImpl implements BillPaymentService{
	
	@Autowired
	private BillPaymentDao billDao;
	
	@Autowired
	private SessionDAO sessionDao;
	
	@Autowired
	private CustomerDAO cDao;
	
	@Autowired
	private BankAccountDao bankAccoundDao;
	
	@Autowired
	private WalletDao walletDao;
	
	@Autowired
	private TransactionDao transactionDao;

	@Override
	public BillPayment makeBillPayment(BillPayment billpayment,String uniqueId) throws InsufficientBalanceException, UserNotLogedinException {
		Optional<CurrentSessionUser> currentUser =  sessionDao.findByUuid(uniqueId);
		
		if(!currentUser.isPresent()) {
			throw new UserNotLogedinException("Please Login first");
		}
		
		Optional<Customer> customer =  cDao.findById(currentUser.get().getUserId());
		Wallet wallet = customer.get().getWallet();
		
		if(wallet.getBalance()<billpayment.getAmount()) {
			throw new InsufficientBalanceException("Insufficient balance in wallet, Add money to your wallet");
		}
		
		wallet.setBalance(wallet.getBalance()-billpayment.getAmount());
		walletDao.save(wallet);
		
		billpayment.setWalletId(wallet.getWalletId());
		billpayment.setTime(LocalDateTime.now());
		
		BillPayment completedPayment = billDao.save(billpayment);
		
		if(completedPayment!=null) {
			Transaction transaction = new Transaction();
			transaction.setDescription(billpayment.getBilltype() +  " successfull");
			transaction.setAmount(billpayment.getAmount());
			transaction.setTransactionDate(LocalDateTime.now());
			transaction.setTransactionType(billpayment.getTransactionType());
			transaction.setWalletId(wallet.getWalletId());
			wallet.getTransaction().add(transaction);
			transactionDao.save(transaction);
		}
		System.out.println(billpayment);
		return completedPayment;
	}

	@Override
	public Set<BillPayment> viewBillPayments(String uniqueId) throws UserNotLogedinException {
		
		
		Optional<CurrentSessionUser> currentUser =  sessionDao.findByUuid(uniqueId);
		
		if(!currentUser.isPresent()) {
			throw new UserNotLogedinException("Please Login first");
		}
		
		Optional<Customer> customer =  cDao.findById(currentUser.get().getUserId());
		Wallet wallet = customer.get().getWallet();
		
		Set<BillPayment> billpaymnets = billDao.findByWalletId(wallet.getWalletId());
		return billpaymnets;
	}

	
	
}
