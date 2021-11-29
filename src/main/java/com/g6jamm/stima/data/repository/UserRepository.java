package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.model.User;

/**
 * @author Mohamad
 */
public interface UserRepository {

  User login(String email, String password);

  User createUser(User user);

  boolean userExists(int id);

  int getNewUserId(User user);

  User getUser(int id);
}
