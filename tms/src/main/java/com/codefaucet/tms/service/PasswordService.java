package com.codefaucet.tms.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.codefaucet.tms.model.service.IPasswordService;

@Service
public class PasswordService implements IPasswordService {

	@Override
	public boolean matches(String plainPassword, String hashedPassword) {
		return plainPassword.equals(hashedPassword);
	}

	@Override
	public String hash(String plainText) {
		return plainText;
	}

	@Override
	public String generateRandom() {
		var validChars = "!@#$2345aAbBcCdDeEfFgGhHijJkKLmMnNoOpPqQrRsStTuUvVwWxXyYzZ67890%^&*";
		var randomPassword = "";
		var randomizer = new Random();
		while (randomPassword.length() < 10) {
			randomPassword += validChars.charAt(randomizer.nextInt(validChars.length()));
		}
		return randomPassword;
	}

}
