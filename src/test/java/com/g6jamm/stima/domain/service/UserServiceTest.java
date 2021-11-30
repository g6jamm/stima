package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.mock.UserRepositoryStub;
import com.g6jamm.stima.domain.exception.LoginException;
import com.g6jamm.stima.domain.exception.SignUpException;
import com.g6jamm.stima.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/** @author Mohamd */
class UserServiceTest {

  @Test
  void loginSuccessfullyReturnCorrectUserTest() throws LoginException {
    UserService userService = new UserService(new UserRepositoryStub());
    String email = "demo@demo.com";
    String password = "demo";
    String expectedName = "John";
    String actualName = userService.login(email, password).getFirstName();
    Assertions.assertEquals(expectedName, actualName);
  }

  @Test
  void loginFailReturnCorrectUserTest() throws LoginException {
    UserService userService = new UserService(new UserRepositoryStub());
    String email = "demo@demo.com";
    String password = "demo";
    String expectedName = "Bo";
    String actualName = userService.login(email, password).getFirstName();
    Assertions.assertNotEquals(expectedName, actualName);
  }

  @Test
  void createNewUserSuccessfullyTest() throws SignUpException {
    UserService userService = new UserService(new UserRepositoryStub());
    String firstName = "Bob";
    String lastName = "Marley";
    String email = "demo420@demo.com";
    String password = "demo";
    User actual = userService.createUser(firstName, lastName, email, password);
    Assertions.assertEquals("demo420@demo.com", actual.getEmail());
  }

  /**
   * Found bug in UserRepositoryStub - generated id didnt consider the users created in the
   * constructor @Author Andreas
   */
  @Test
  void createUserUserIdIncrements() throws SignUpException {
    UserService userService = new UserService(new UserRepositoryStub());
    String firstName = "Bob";
    String lastName = "Marley";
    String email = "demo420@demo.com";
    String password = "demo";
    User actual = userService.createUser(firstName, lastName, email, password);
    Assertions.assertEquals(3, actual.getId());
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
  void getUserByIdSuccessfullTest() {
    UserService userService = new UserService(new UserRepositoryStub());
    User actualUser = userService.getUser(1);
    assertEquals(1, actualUser.getId());
  }

  @Test
  void getUserByIdFailTest() {
    UserService userService = new UserService(new UserRepositoryStub());
    User actualUser = userService.getUser(2);
    assertNotEquals(1, actualUser.getId());
  }

  @Test
  void userExistsSuccessfullyTest() {
    UserService userService = new UserService(new UserRepositoryStub());
    boolean doesUserExist = userService.userExists(2);

    Assertions.assertEquals(true, doesUserExist);
  }

  @Test
  void userExistsFailTest() {
    UserService userService = new UserService(new UserRepositoryStub());
    boolean doesUserExist = userService.userExists(68);

    Assertions.assertNotEquals(true, doesUserExist);
  }
}
