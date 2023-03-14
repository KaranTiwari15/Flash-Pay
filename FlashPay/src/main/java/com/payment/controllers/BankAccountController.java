package com.payment.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.exception.BankAccountNotExsists;
import com.payment.exception.BankAlreadyAdded;
import com.payment.exception.NotAnyBankAddedYet;
import com.payment.exception.UserNotLogedinException;
import com.payment.model.BankAccount;
import com.payment.service.BankAccountService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/bank")
public class BankAccountController {
	
	@Autowired
	private BankAccountService bankService;

	@PostMapping("/{id}")
	public  ResponseEntity<BankAccount> addBankAccountToWallet(@RequestBody BankAccount bankaccount,@PathVariable("id") String uniqueId) throws BankAlreadyAdded, UserNotLogedinException {
		BankAccount aaccountAdded = bankService.addBank(bankaccount,uniqueId);
		return new ResponseEntity<>(bankaccount,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{acc}/{id}")
	public  ResponseEntity<BankAccount> deleteBankAccountfromWallet( @PathVariable("acc") Integer accountNumber,@PathVariable("id") String uniqueId) throws BankAccountNotExsists, UserNotLogedinException {
		BankAccount accountDeleted = bankService.removeBank(accountNumber, uniqueId);
		return new ResponseEntity<BankAccount>(accountDeleted,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{acc}/{id}")
	public  ResponseEntity<BankAccount> viewBankAccountDetails( @PathVariable("acc") Integer accountNumber,@PathVariable("id") String uniqueId) throws BankAccountNotExsists, UserNotLogedinException {
		BankAccount accountDetails = bankService.viewBankAccountI(accountNumber, uniqueId);
		return new ResponseEntity<BankAccount>(accountDetails,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<BankAccount> viewAllAccountDetails(@PathVariable("id") String uniqueId) throws NotAnyBankAddedYet, UserNotLogedinException, BankAccountNotExsists {
		BankAccount accountDetails = bankService.viewAllAccount(uniqueId);
		return new ResponseEntity<BankAccount>(accountDetails,HttpStatus.ACCEPTED);
	}
	
	
	
}
