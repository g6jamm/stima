package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.PermissionRepository;
import com.g6jamm.stima.data.repository.ResourceTypeRepository;
import com.g6jamm.stima.data.repository.UserRepository;
import com.g6jamm.stima.domain.exception.LoginException;
import com.g6jamm.stima.domain.exception.ResourceTypeNotFoundException;
import com.g6jamm.stima.domain.exception.SignUpException;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Permission;
import com.g6jamm.stima.domain.model.User;

import java.util.List;

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
   * Returns a user if user exist in database.
   *
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
   * Returns a user with the new ID.
   *
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
            .resourceType(RESOURCE_TYPE_REPOSITORY.getByResourceTypeName(resourceType))
            .permission(PERMISSION_REPOSITORY.getPermission(permission))
            .password(password)
            .build();

    return USER_REPOSITORY.createUser(user);
  }

  /**
   * Return true if user exists. Else return false.
   *
   * @author Mohamad
   */
  public boolean userExists(int userId) throws SystemException {
    return USER_REPOSITORY.userExists(userId);
  }

  /**
   * Returns a list of perssions.
   */
  public List<Permission> getPermissions() throws SystemException {
    return PERMISSION_REPOSITORY.getPermissions();
  }
}
