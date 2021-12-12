package com.g6jamm.stima.data.repository.stub;

import com.g6jamm.stima.domain.exception.SystemException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserRepositoryStubTest {

  @Test
  void login() throws SystemException {
    UserRepositoryStub userRepositoryStub = new UserRepositoryStub();
    String email = "demo@demo.com";
    String password = "demo";
    String expectedName = "John";

    Assertions.assertEquals(expectedName, userRepositoryStub.login(email, password).getFirstName());
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
