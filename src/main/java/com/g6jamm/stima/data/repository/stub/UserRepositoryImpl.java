package com.g6jamm.stima.data.repository.stub;

import com.g6jamm.stima.data.repository.UserRepository;
import com.g6jamm.stima.domain.exception.SignUpException;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Permission;
import com.g6jamm.stima.domain.model.ResourceType;
import com.g6jamm.stima.domain.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRepositoryImpl implements UserRepository {

  public static List<User> USER_LIST = new ArrayList<>();

  /**
   * Creates stub data when instantiated and adds to userListStub List.
   *
   * @author Mohamad
   */
  public UserRepositoryImpl() {
    if (USER_LIST.isEmpty()) {
      User user =
          new User.UserBuilder()
              .firstName("John")
              .lastName("Doe")
              .email("demo@demo.com")
              .password("demo")
              .id(USER_LIST.size() + 1)
              .resourceType(new ResourceType.ResourceTypeBuilder().name("Junior Developer").build())
              .permission(new Permission.PermissionBuilder().name("user").build())
              .build();

      User user2 =
          new User.UserBuilder()
              .firstName("Jane")
              .lastName("Doe")
              .email("maill@mail.com")
              .password("123")
              .id(USER_LIST.size() + 1)
              .resourceType(new ResourceType.ResourceTypeBuilder().name("Junior Developer").build())
              .permission(new Permission.PermissionBuilder().name("user").build())
              .build();

      USER_LIST.add(user);
      USER_LIST.add(user2);
    }
  }

  /**
   * @return User if user exist in stub data, else return null
   * @author Mohamad
   */
  @Override
  public User login(String email, String password) throws SystemException {

    for (User u : USER_LIST) {
      if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
        return u;
      }
    }
    return null;
  }

  /**
   * @return New user, and assigns ID to user
   * @author Mohamad
   */
  @Override
  public User createUser(User user) throws SignUpException, SystemException {
    if (emailExists(user.getEmail())) {
      throw new SignUpException("Email already in use");
    }
    user =
        new User.UserBuilder()
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .password(user.getPassword())
            .id(USER_LIST.size() + 1)
            .resourceType(new ResourceType.ResourceTypeBuilder().name("Junior Developer").build())
            .permission(new Permission.PermissionBuilder().name("user").build())
            .build();
    USER_LIST.add(user);

    return user;
  }

  /**
   * @return true if user exists in list
   * @author Mohamad
   */
  @Override
  public boolean userExists(int userId) throws SystemException {
    return USER_LIST.stream().anyMatch(user -> userId == user.getId());
  }

  private boolean emailExists(String email) {
    return USER_LIST.stream().anyMatch(user -> Objects.equals(email, user.getEmail()));
  }

  /**
   * @return New user ID
   * @author Mohamad
   */
  @Override
  public int getNewUserId(User user) throws SystemException {

    user =
        new User.UserBuilder()
            .firstName("John")
            .lastName("Doe")
            .email("demo@demo.com")
            .password("demo")
            .id(USER_LIST.size() + 1)
            .resourceType(new ResourceType.ResourceTypeBuilder().name("Junior Developer").build())
            .permission(new Permission.PermissionBuilder().name("user").build())
            .build();

    return user.getId();
  }
}
