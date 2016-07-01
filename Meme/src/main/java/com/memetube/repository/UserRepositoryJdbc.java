package com.memetube.repository;

import org.springframework.stereotype.Repository;

import com.memetube.models.User;

@Repository
public class UserRepositoryJdbc implements UserRepository {

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
