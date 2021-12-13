package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.stub.ResourceTypeRepositoryImpl;
import com.g6jamm.stima.data.repository.stub.PermissionRepositoryImpl;
import com.g6jamm.stima.data.repository.stub.UserRepositoryImpl;
import com.g6jamm.stima.domain.exception.LoginException;
import com.g6jamm.stima.domain.exception.ResourceTypeNotFoundException;
import com.g6jamm.stima.domain.exception.SignUpException;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/** @author Mohamd */
class UserServiceTest {

  /**
   * Found bug in UserRepositoryStub - generated id didnt consider the users created in the
   * constructor @Author Andreas
   */
  @Test
  void createUserUserIdIncrements()
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

    Assertions.assertEquals(3, actual.getId());
  }

  @Test
  void loginSuccessfullyReturnCorrectUserTest() throws LoginException, SystemException {
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
  void loginFailReturnCorrectUserTest() throws LoginException, SystemException {
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
  void createNewUserSuccessfullyTest()
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

  //  @Test
  //  void createNewUserUserAlreadyExistFailTest() throws LoginException {
  //    UserService userService = new UserService(new UserRepositoryStub());
  //    String firstName = "Bob";
  //    String lastName = "Marley";
  //    String email = "demo@demo.com";
  //    String password = "demo";
  //    assertThrows(LoginException.class, () ->
  // userService.createUser(firstName,lastName,email,password));
  //
  //  }

  @Test
  void getUserByIdSuccessfullTest() throws SystemException {
    UserService userService =
        new UserService(
            new UserRepositoryImpl(),
            new ResourceTypeRepositoryImpl(),
            new PermissionRepositoryImpl());
    User actualUser = userService.getUser(1);
    assertEquals(1, actualUser.getId());
  }

  @Test
  void getUserByIdFailTest() throws SystemException {
    UserService userService =
        new UserService(
            new UserRepositoryImpl(),
            new ResourceTypeRepositoryImpl(),
            new PermissionRepositoryImpl());
    User actualUser = userService.getUser(1);
    assertNotEquals(0, actualUser.getId());
  }

  @Test
  void userExistsSuccessfullyTest() throws SystemException {
    UserService userService =
        new UserService(
            new UserRepositoryImpl(),
            new ResourceTypeRepositoryImpl(),
            new PermissionRepositoryImpl());
    boolean doesUserExist = userService.userExists(1);

    Assertions.assertEquals(true, doesUserExist);
  }

  @Test
  void userExistsFailTest() throws SystemException {
    UserService userService =
        new UserService(
            new UserRepositoryImpl(),
            new ResourceTypeRepositoryImpl(),
            new PermissionRepositoryImpl());
    boolean doesUserExist = userService.userExists(68);

    Assertions.assertNotEquals(true, doesUserExist);
  }
}
