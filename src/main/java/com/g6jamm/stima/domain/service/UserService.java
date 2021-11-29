package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.UserRepository;
import com.g6jamm.stima.domain.model.User;

public class UserService {

  UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User login(String email, String password) {
    return userRepository.login(email, password);
  }

  public User createUser(String firstName, String lastName, String email, String password) {
    User user =
        new User.UserBuilder()
            .firstName(firstName)
            .lastName(lastName)
            .email(email)
            .password(password)
            .build();
    return userRepository.createUser(user);
  }
}
