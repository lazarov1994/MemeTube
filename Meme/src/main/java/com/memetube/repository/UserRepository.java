package com.memetube.repository;

import com.memetube.models.User;

public interface UserRepository {

	User getUserByUsername(String username);

}
