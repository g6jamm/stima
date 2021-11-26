package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.model.User;

public interface UserRepository {

  boolean login(String email, String password);

  User createUser(User user);

  boolean userExists(int id);

  int getNewUserId(User user);

  User getUser(int id);
}
