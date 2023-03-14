package com.payment.exception;

public class TransactionNotFoundException extends Exception{
	
	public TransactionNotFoundException (){
		
	}
	
	public TransactionNotFoundException (String message){
		super(message);
	}
}
