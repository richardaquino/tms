package com.codefaucet.tms.model.repository;

import com.codefaucet.tms.model.User;

public interface IUserRepository {

	User findByUsername(String username);

}
