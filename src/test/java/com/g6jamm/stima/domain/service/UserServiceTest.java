package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.stub.PermissionRepositoryImpl;
import com.g6jamm.stima.data.repository.stub.ResourceTypeRepositoryImpl;
import com.g6jamm.stima.data.repository.stub.UserRepositoryImpl;
import com.g6jamm.stima.domain.exception.LoginException;
import com.g6jamm.stima.domain.exception.ResourceTypeNotFoundException;
import com.g6jamm.stima.domain.exception.SignUpException;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/** @author Mohamd */
class UserServiceTest {

  /**
   * Found bug in UserRepositoryStub - generated id didnt consider the users created in the
   * constructor @Author Andreas
   */
  @Test
  void testCreateUserUserIdIncrements()
      throws SignUpException, SystemException, ResourceTypeNotFoundException {
    UserService userService =
        new UserService(
            new UserRepositoryImpl(),
            new ResourceTypeRepositoryImpl(),
            new PermissionRepositoryImpl());
    String firstName = "Bobby";
    String lastName = "Olsen";
    String email = "newtest@demo.com";
    String password = "demo";
    String resourceType = "Junior Developer";
    String permission = "user";
    User actual =
        userService.createUser(firstName, lastName, email, password, resourceType, permission);

    Assertions.assertEquals(4, actual.getId());
  }

  @Test
  void testLoginSuccessfullyReturnCorrectUser() throws LoginException, SystemException {
    UserService userService =
        new UserService(
            new UserRepositoryImpl(),
            new ResourceTypeRepositoryImpl(),
            new PermissionRepositoryImpl());
    String email = "demo@demo.com";
    String password = "demo";
    String expectedName = "John";
    String actualName = userService.login(email, password).getFirstName();
    Assertions.assertEquals(expectedName, actualName);
  }

  @Test
  void testLoginFailReturnCorrectUser() throws LoginException, SystemException {
    UserService userService =
        new UserService(
            new UserRepositoryImpl(),
            new ResourceTypeRepositoryImpl(),
            new PermissionRepositoryImpl());
    String email = "demo@demo.com";
    String password = "demo";
    String expectedName = "Bo";
    String actualName = userService.login(email, password).getFirstName();
    Assertions.assertNotEquals(expectedName, actualName);
  }

  @Test
  void testCreateNewUserSuccessfully()
      throws SignUpException, SystemException, ResourceTypeNotFoundException {
    UserService userService =
        new UserService(
            new UserRepositoryImpl(),
            new ResourceTypeRepositoryImpl(),
            new PermissionRepositoryImpl());
    String firstName = "Bob";
    String lastName = "Marley";
    String email = "demo420@demo.com";
    String password = "demo";
    String resourceType = "Junior Developer";
    String permission = "user";
    User actual =
        userService.createUser(firstName, lastName, email, password, resourceType, permission);
    Assertions.assertEquals("demo420@demo.com", actual.getEmail());
  }

  @Test
  void testUserExistsSuccessfully() throws SystemException {
    UserService userService =
        new UserService(
            new UserRepositoryImpl(),
            new ResourceTypeRepositoryImpl(),
            new PermissionRepositoryImpl());
    boolean doesUserExist = userService.userExists(1);

    Assertions.assertTrue(doesUserExist);
  }

  @Test
  void testExistsFail() throws SystemException {
    UserService userService =
        new UserService(
            new UserRepositoryImpl(),
            new ResourceTypeRepositoryImpl(),
            new PermissionRepositoryImpl());
    boolean doesUserExist = userService.userExists(68);

    Assertions.assertNotEquals(true, doesUserExist);
  }
}
