package com.example.stima.data.repository.mock;

import com.example.stima.data.repository.UserRepository;
import com.example.stima.domain.model.Role;
import com.example.stima.domain.model.User;

public class UserRepositoryStub implements UserRepository {


  @Override
  public boolean login(String email, String password) {
    return false;
  }

  @Override
  public User createUser(User user) {
    user = new User.UserBuilder()
        .firstName("John")
        .lastName("Doe")
        .email("maill@mail.com")
        .password("123")
        .id(1)
        .role(new Role())
        .build();
    return user;
  }

  @Override
  public boolean userExists(int id) {
    int stubId = 1;
    if (id == stubId){
      return true;
    }
    return false;
  }

  @Override
  public int getNewUserId(User user) {
    return 0;
  }

  @Override
  public User getUser(int id) {
    return null;
  }
}
