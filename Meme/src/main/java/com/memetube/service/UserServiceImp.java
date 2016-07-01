package com.memetube.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.stereotype.Service;

import com.memetube.models.User;
import com.memetube.repository.UserRepository;

@Transactional
@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserRepository ur;
	
	

	@Override
	public void authenticateUser(UserCredentials login) {
		User user = ur.getUserByUsername(login.getUsername());
		// TODO Auto-generated method stub
		
	}

}
