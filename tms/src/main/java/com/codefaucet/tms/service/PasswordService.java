package com.codefaucet.tms.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.codefaucet.tms.model.service_interface.IPasswordService;

@Service
public class PasswordService implements IPasswordService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public boolean matches(String plainPassword, String hashedPassword) {
		return passwordEncoder.matches(plainPassword, hashedPassword);
	}

	@Override
	public String hash(String plainText) {
		return passwordEncoder.encode(plainText);
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
