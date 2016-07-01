package com.memetube.service;

import org.springframework.data.authentication.UserCredentials;

public interface UserService {

	void authenticateUser(UserCredentials login);
}
