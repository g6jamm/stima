package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.UserRepository;
import com.g6jamm.stima.domain.exception.LoginException;
import com.g6jamm.stima.domain.model.User;

/**
 * @author Mohamad
 */
public class UserService {

  UserRepository userRepository;


  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * @param email
   * @param password
   * @return User if user exist in DB
   * @throws LoginException
   * @author Mohamad
   */
  public User login(String email, String password) throws LoginException {
    return userRepository.login(email, password);
  }

  /**
   * @param firstName
   * @param lastName
   * @param email
   * @param password
   * @return New User with ID
   * @throws LoginException
   * @author Mohamad
   */
  public User createUser(String firstName, String lastName, String email, String password)
      throws LoginException {
    User user =
        new User.UserBuilder()
            .firstName(firstName)
            .lastName(lastName)
            .email(email)
            .password(password)
            .build();
    return userRepository.createUser(user);
  }

  /**
   * @param userId
   * @return User if exists
   * @author Mohamad
   */
  public User getUser(int userId) {
    return userRepository.getUser(userId);
  }

  /**
   * @param userId
   * @return true if userId is valid
   * @author Mohamad
   */
  public boolean isValidUser(int userId) {
    return userExists(userId);
  }

  /**
   * @param userId
   * @return true if user exists
   * @author Mohamad
   */
  public boolean userExists(int userId) {
    return userRepository.userExists(userId);
  }
}
