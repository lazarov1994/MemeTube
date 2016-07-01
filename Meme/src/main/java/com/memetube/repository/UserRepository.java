package com.memetube.repository;

import com.memetube.models.User;

public interface UserRepository {
    public void insertUser(String username, String password);

    public void userVote(String username, int memeId, boolean upvote);

    public User getUserByUsername(String username);
}
