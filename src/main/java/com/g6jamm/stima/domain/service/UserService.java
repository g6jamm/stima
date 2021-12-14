package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.PermissionRepository;
import com.g6jamm.stima.data.repository.ResourceTypeRepository;
import com.g6jamm.stima.data.repository.UserRepository;
import com.g6jamm.stima.domain.exception.LoginException;
import com.g6jamm.stima.domain.exception.ResourceTypeNotFoundException;
import com.g6jamm.stima.domain.exception.SignUpException;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.User;

/** @author Mohamad */
public class UserService {

  private final UserRepository USER_REPOSITORY;
  private final ResourceTypeRepository RESOURCE_TYPE_REPOSITORY;
  private final PermissionRepository PERMISSION_REPOSITORY;

  public UserService(
      UserRepository userRepository,
      ResourceTypeRepository resourceTypeRepository,
      PermissionRepository permissionRepository) {
    this.USER_REPOSITORY = userRepository;
    this.RESOURCE_TYPE_REPOSITORY = resourceTypeRepository;
    this.PERMISSION_REPOSITORY = permissionRepository;
  }

  /**
   * @param email
   * @param password
   * @return User if user exist in DB
   * @throws LoginException
   * @author Mohamad
   */
  public User login(String email, String password) throws LoginException, SystemException {
    User user = USER_REPOSITORY.login(email, password);
    if (user != null) {
      return user;
    }
    throw new LoginException("Wrong email or password");
  }

  /**
   * @param firstName
   * @param lastName
   * @param email
   * @param password
   * @return New User with the new ID
   * @throws SignUpException
   * @author Mohamad
   */
  public User createUser(
      String firstName,
      String lastName,
      String email,
      String password,
      String resourceType,
      String permission)
      throws SignUpException, SystemException, ResourceTypeNotFoundException {
    User user =
        new User.UserBuilder()
            .firstName(firstName)
            .lastName(lastName)
            .email(email)
            .resourceType(RESOURCE_TYPE_REPOSITORY.findByName(resourceType))
            .permission(PERMISSION_REPOSITORY.findByName(permission))
            .password(password)
            .build();
    return USER_REPOSITORY.createUser(user);
  }

  /**
   * @param userId
   * @return true if user exists
   * @author Mohamad
   */
  public boolean userExists(int userId) throws SystemException {
    return USER_REPOSITORY.userExists(userId);
  }
}
