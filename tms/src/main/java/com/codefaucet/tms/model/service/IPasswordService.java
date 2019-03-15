package com.codefaucet.tms.model.service;

public interface IPasswordService {

	boolean matches(String plainPassword, String hashedPassword);
	
	String hash(String plainText);
	
	String generateRandom();

}
