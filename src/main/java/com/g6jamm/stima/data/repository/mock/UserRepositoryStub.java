package com.g6jamm.stima.data.repository.mock;

import com.g6jamm.stima.data.repository.UserRepository;
import com.g6jamm.stima.domain.exception.SignUpException;
import com.g6jamm.stima.domain.model.Role;
import com.g6jamm.stima.domain.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryStub implements UserRepository {

  public static List<User> userListStub = new ArrayList<>();

  /**
   * Creates stub data when instantiated and adds to userListStub List.
   *
   * @author Mohamad
   */
  public UserRepositoryStub() {
    if(userListStub.isEmpty()) {
      User user =
              new User.UserBuilder()
                      .firstName("John")
                      .lastName("Doe")
                      .email("demo@demo.com")
                      .password("demo")
                      .id(userListStub.size() + 1)
                      .role(new Role())
                      .build();

      User user2 =
              new User.UserBuilder()
                      .firstName("Jane")
                      .lastName("Doe")
                      .email("maill@mail.com")
                      .password("123")
                      .id(userListStub.size() + 1)
                      .role(new Role())
                      .build();

      userListStub.add(user);
      userListStub.add(user2);
    }
  }

  /**
   * @param email
   * @param password
   * @return User if user exist in stub data, else rturns null
   * @author Mohamad
   */
  @Override
  public User login(String email, String password) {

    for (User u : userListStub) {
      if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
        return u;
      }
    }
    return null;
  }

  /**
   * @param user
   * @return New user, and assigns ID to user
   * @author Mohamad
   */
  @Override
  public User createUser(User user) throws SignUpException {
    if (emailExists(user.getEmail())) {
      throw new SignUpException("Email already in use");
    }
    user =
        new User.UserBuilder()
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .password(user.getPassword())
            .id(userListStub.size()+1)
            .role(user.getRole())
            .build();
    userListStub.add(user);

    return user;
  }

  /**
   * @param id
   * @return true if user exists in list
   * @author Mohamad
   */
  @Override
  public boolean userExists(int id) {
    return userListStub.stream().anyMatch(user -> id == user.getId());
  }

  private boolean emailExists(String email) {
    return userListStub.stream().anyMatch(user -> email == user.getEmail());
  }

  /**
   * @param user
   * @return New user ID
   * @author Mohamad
   */
  @Override
  public int getNewUserId(User user) {

    user =
        new User.UserBuilder()
            .firstName("John")
            .lastName("Doe")
            .email("demo@demo.com")
            .password("demo")
            .id(userListStub.size()+1)
            .role(new Role())
            .build();

    return user.getId();
  }

  /**
   * @param id
   * @return user with user ID that matches param ID
   * @author Mohamad
   */
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
