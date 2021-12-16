package com.g6jamm.stima.data.repository.mysql.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DbManagerTest {

  @Test
  void testConnection() {

    java.sql.Connection result = DbManager.getInstance().getConnection();
    Assertions.assertNotNull(result);
  }
}
