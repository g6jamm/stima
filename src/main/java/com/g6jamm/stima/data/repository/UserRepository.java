package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.exception.SignUpException;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.User;

public interface UserRepository {

  User login(String email, String password) throws SystemException;

  User createUser(User user) throws SignUpException, SystemException;

  boolean userExists(int id) throws SystemException;

  int getNewUserId(User user) throws SystemException;
}
