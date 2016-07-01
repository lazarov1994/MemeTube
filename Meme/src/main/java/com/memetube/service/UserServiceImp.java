package com.memetube.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.memetube.Constants;
import com.memetube.models.User;
import com.memetube.repository.UserRepository;

@Transactional
@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserRepository ur;

	@Override
	public boolean authenticateUser(String username, String password) {
		User user = ur.getUserByUsername(username);

		return checkPassword(password, user.getPassword());

	}

	@Override
	public void insertUser(String username, String password) {

		ur.insertUser(username, hashPassword(password));
		// TODO Auto-generated method stub
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	private static String hashPassword(String password_plaintext) {
		String salt = BCrypt.gensalt(Constants.BCRYPT_WORKLOAD);
		String hashed_password = BCrypt.hashpw(password_plaintext, salt);

		return (hashed_password);
	}

	private static boolean checkPassword(String password_plaintext, String stored_hash) {
		boolean password_verified = false;

		if (null == stored_hash || !stored_hash.startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

		password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

		return (password_verified);
	}

}
