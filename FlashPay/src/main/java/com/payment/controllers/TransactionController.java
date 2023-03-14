package com.payment.controllers;
import com.payment.exception.CustomerNotException;
import com.payment.exception.TransactionNotFoundException;
import com.payment.exception.UserNotLogedinException;
import com.payment.model.Transaction;
import com.payment.model.TransactionType;
import com.payment.model.Wallet;
import com.payment.repository.TransactionDao;
import com.payment.service.TranscationServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	private TranscationServiceImpl transactionserviceimpl;
	
	@Autowired
	private TransactionDao dao;
	
	@GetMapping("/transation_histroy/{uniqueId}")
	public ResponseEntity<List<Transaction>> viewAllTransactionHandler( @PathVariable("uniqueId") String uniqueId) throws CustomerNotException, UserNotLogedinException, TransactionNotFoundException{
		List<Transaction> allTransaction = transactionserviceimpl.viewAlltransaction(uniqueId);
		return new ResponseEntity<List<Transaction>>(allTransaction,HttpStatus.OK);
	}
	
	
	@GetMapping("/{from}/{to}/{id}")
	public ResponseEntity<List<Transaction>> viewTransactionByDatehandler(@PathVariable("from") String from,
			@PathVariable("to") String to,@PathVariable("id") String uniqueId) throws CustomerNotException, UserNotLogedinException, TransactionNotFoundException{
		List<Transaction> historyByDate= transactionserviceimpl.viewTranscationByDate(from,to,uniqueId);	
		return new ResponseEntity<List<Transaction>>(historyByDate,HttpStatus.OK);
	}
	
	@GetMapping("/historybytype/{transactiontype}/{uniqueId}")
	public ResponseEntity<List<Transaction>> viewAllTransactionByTypeHandler(@PathParam("transactiontype") TransactionType type,@PathVariable String uniqueId) throws CustomerNotException, UserNotLogedinException, TransactionNotFoundException{	
		List<Transaction> TransactionType = transactionserviceimpl.viewAllTransactionbyTransactionType(uniqueId, type);
		return new ResponseEntity<List<Transaction>>(TransactionType,HttpStatus.OK);
	}
	
	
}
