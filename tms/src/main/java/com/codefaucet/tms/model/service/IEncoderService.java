package com.codefaucet.tms.model.service;

public interface IEncoderService {

	String encode(String plainText);
	
	String decode(String encodedText);
	
}
