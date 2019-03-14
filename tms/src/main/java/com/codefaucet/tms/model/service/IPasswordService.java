package com.codefaucet.tms.model.service;

public interface IPasswordService {

	boolean matches(String password, String hashedPassword);

}
