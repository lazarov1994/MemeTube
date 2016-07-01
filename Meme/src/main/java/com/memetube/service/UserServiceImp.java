package com.memetube.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memetube.models.User;
import com.memetube.repository.UserRepository;

@Transactional
@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserRepository ur;
	

	@Override
	public void authenticateUser(String username, String passwor) {
		User user = ur.getUserByUsername(username);
		
	}

    @Autowired
    UserRepository userRepo;

    @Override
    public void insertUser(String username, String password) {
        // TODO Auto-generated method stub
    }

    @Override
    public User getUserByUsername(String username) {
        // TODO Auto-generated method stub
        return null;
    }
}
