package com.g6jamm.stima.data.repository.mysql.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/** @author Mathias */
public class DbManager {
  private static DbManager instance;
  private Connection connection;

  /**
   * Create a connection to the database using properties defined in application.properties.
   *
   * @author Mathias
   */
  private DbManager() {
    Properties properties = DbSelector.selectConnection();
    String url = properties.getProperty("url");
    String user = properties.getProperty("user");
    String password = properties.getProperty("password");
    try {
      this.connection = DriverManager.getConnection(url, user, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns a new instance of DbManager.
   *
   * @author Mathias
   */
  public static DbManager getInstance() {
    if (null == instance) {
      instance = new DbManager();
    } else {
      try {
        if (instance.getConnection().isClosed()) {
          instance = new DbManager();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return instance;
  }

  /**
   * Returns the connection of the instance.
   *
   * @author Mathias
   */
  public Connection getConnection() {
    return connection;
  }
}
