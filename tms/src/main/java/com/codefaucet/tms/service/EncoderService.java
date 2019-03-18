package com.codefaucet.tms.service;

import java.util.Base64;

import org.springframework.stereotype.Service;

import com.codefaucet.tms.model.service_interface.IEncoderService;

@Service
public class EncoderService implements IEncoderService {

	@Override
	public String encode(String plainText) {
		return new String(Base64.getEncoder().encode(plainText.getBytes()));
	}

	@Override
	public String decode(String encodedText) {
		return new String(Base64.getDecoder().decode(encodedText));
	}

}
