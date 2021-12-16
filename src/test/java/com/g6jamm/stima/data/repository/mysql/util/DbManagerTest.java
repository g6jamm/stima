package com.g6jamm.stima.data.repository.mysql.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

class DbManagerTest {

  @Test
  void testConnection() {

    Connection result = DbManager.getInstance().getConnection();

    Assertions.assertNotNull(result);
  }

  @Test
  void testIfConnectionIsSingleton() {
    Connection connection1 = DbManager.getInstance().getConnection();
    Connection connection2 = DbManager.getInstance().getConnection();

    Assertions.assertEquals(connection1, connection2);
  }
}
