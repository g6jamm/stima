package com.g6jamm.stima.data.repository.stub;

import com.g6jamm.stima.domain.exception.SystemException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserRepositoryStubTest {

  @Test
  void testLogin() throws SystemException {
    UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl();
    String email = "demo@demo.com";
    String password = "demo";
    String expectedName = "John";

    Assertions.assertEquals(expectedName, userRepositoryImpl.login(email, password).getFirstName());
  }
}
