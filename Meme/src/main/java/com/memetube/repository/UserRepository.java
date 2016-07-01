package com.memetube.repository;

import com.memetube.models.User;

public interface UserRepository {
    public void insertUser(String username, String password);

    public User getUserByUsername(String username);
}
