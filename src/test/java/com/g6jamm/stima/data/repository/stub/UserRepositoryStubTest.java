package com.g6jamm.stima.data.repository.stub;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserRepositoryStubTest {

  @Test
  void login() {
    UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl();
    String email = "demo@demo.com";
    String password = "demo";
    String expectedName = "John";

    Assertions.assertEquals(expectedName, userRepositoryImpl.login(email, password).getFirstName());
  }

  @Test
  void createUser() {}

  @Test
  void userExists() {}

  @Test
  void getNewUserId() {}

  @Test
  void getUser() {}
}
