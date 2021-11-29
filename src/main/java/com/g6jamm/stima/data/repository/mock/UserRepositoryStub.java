package com.g6jamm.stima.data.repository.mock;

import com.g6jamm.stima.data.repository.UserRepository;
import com.g6jamm.stima.domain.model.Role;
import com.g6jamm.stima.domain.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryStub implements UserRepository {

  public static List<User> userListStub = new ArrayList<>();

  public UserRepositoryStub() {
    User user =
        new User.UserBuilder()
            .firstName("John")
            .lastName("Doe")
            .email("demo@demo.com")
            .password("demo")
            .id(1)
            .role(new Role())
            .build();

    User user2 =
        new User.UserBuilder()
            .firstName("Jane")
            .lastName("Doe")
            .email("maill@mail.com")
            .password("123")
            .id(2)
            .role(new Role())
            .build();

    userListStub.add(user);
    userListStub.add(user2);
  }

  @Override
  public User login(String email, String password) {

    for (User u : userListStub) {
      if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
        return u;
      }
    }
    return null;
  }

  @Override
  public User createUser(User user) {

    int generatedIdStub = 1;

    user =
        new User.UserBuilder()
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .password(user.getPassword())
            .id(generatedIdStub)
            .role(user.getRole())
            .build();
    userListStub.add(user);

    return user;
  }

  @Override
  public boolean userExists(int id) {
    return userListStub.stream().anyMatch(user -> id == user.getId());
  }

  @Override
  public int getNewUserId(User user) {
    user =
        new User.UserBuilder()
            .firstName("John")
            .lastName("Doe")
            .email("demo@demo.com")
            .password("demo")
            .id(1)
            .role(new Role())
            .build();

    return user.getId();
  }

  @Override
  public User getUser(int id) {
    User result = null;

    for (User u : userListStub) {
      if (u.getId() == id) {
        result = u;
      }
    }
    return result;
  }
}
