package com.g6jamm.stima.data.repository.mock;

import com.g6jamm.stima.data.repository.UserRepository;
import com.g6jamm.stima.domain.model.Role;
import com.g6jamm.stima.domain.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryStub implements UserRepository {

  @Override
  public User login(String email, String password) {
    User user = null;
    return user;
  }

  @Override
  public User createUser(User user) {
    user =
        new User.UserBuilder()
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
    if (id == stubId) {
      return true;
    }
    return false;
  }

  @Override
  public int getNewUserId(User user) {
    user =
        new User.UserBuilder()
            .firstName("John")
            .lastName("Doe")
            .email("maill@mail.com")
            .password("123")
            .id(1)
            .role(new Role())
            .build();

    return user.getId();
  }

  @Override
  public User getUser(int id) {

    List<User> users = new ArrayList<>();
    User result = null;
    User user1 =
        new User.UserBuilder()
            .firstName("John")
            .lastName("Doe")
            .email("maill@mail.com")
            .password("123")
            .id(1)
            .role(new Role())
            .build();

    User user2 =
        new User.UserBuilder()
            .firstName("John")
            .lastName("Doe")
            .email("maill@mail.com")
            .password("123")
            .id(2)
            .role(new Role())
            .build();

    users.add(user1);
    users.add(user2);

    for (User u : users) {
      if (u.getId() == id) {
        result = u;
      }
    }
    return result;
  }
}
