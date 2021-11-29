package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.mock.UserRepositoryStub;
import com.g6jamm.stima.domain.exception.LoginException;
import com.g6jamm.stima.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

  @Test
  void loginSuccesfullyReturnCorrectUserTest() throws LoginException {
    UserService userService = new UserService(new UserRepositoryStub());
    String email = "demo@demo.com";
    String password = "demo";
    String expectedName = "John";
    String actualName =  userService.login(email,password).getFirstName();
    Assertions.assertEquals(expectedName,actualName);
  }

  @Test
  void createNewUserTest() throws LoginException {
    UserService userService = new UserService(new UserRepositoryStub());
    String firstName = "Bob";
    String lastName = "Marley";
    String email = "demo420@demo.com";
    String password = "demo";
    User actual = userService.createUser(firstName,lastName,email,password);
    Assertions.assertEquals("demo420@demo.com", actual.getEmail());

  }

  @Test
  void getUserByIDSuccesfullTest() {
    UserService userService = new UserService(new UserRepositoryStub());
    User actualUser = userService.getUser(1);
    assertEquals(1, actualUser.getId());

  }

  @Test
  void isValidUserByIdTest() {
    UserService userService = new UserService(new UserRepositoryStub());
    boolean isValidUser = userService.isValidUser(1);

    Assertions.assertEquals(true, isValidUser);

  }

  @Test
  void userExists() {
    UserService userService = new UserService(new UserRepositoryStub());
    boolean doesUserExist = userService.userExists(2);

    Assertions.assertEquals(true, doesUserExist);
  }
}