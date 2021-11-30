package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.exception.SignUpException;
import com.g6jamm.stima.domain.model.User;

/** @author Mohamad */
public interface UserRepository {

  User login(String email, String password);

  User createUser(User user) throws SignUpException;

  boolean userExists(int id);

  int getNewUserId(User user);

  User getUser(int id);
}
