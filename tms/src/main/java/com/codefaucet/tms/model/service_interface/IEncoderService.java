package com.codefaucet.tms.model.service_interface;

public interface IEncoderService {

	String encode(String plainText);
	
	String decode(String encodedText);
	
}
