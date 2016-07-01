package com.memetube.service;

import com.memetube.models.User;

public interface UserService {
	public void insertUser(String username, String password);

	public boolean authenticateUser(String username, String password);

	public User getUserByUsername(String username);
}
