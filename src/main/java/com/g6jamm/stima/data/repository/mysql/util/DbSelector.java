package com.g6jamm.stima.data.repository.mysql.util;

import com.g6jamm.stima.data.repository.mysql.util.type.ConnectionType;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbSelector {

  private static ConnectionType connectionType;

  /**
   * Returns properties depending on localhost or Heroku. This is done to hide sensitive data on
   * GitHub from the production env.
   *
   * @auther Andreas, Mathias
   */
  public static Properties selectConnection() {
    Properties properties = new Properties();

    try (InputStream input = new ClassPathResource("application.properties").getInputStream()) {
      properties.load(input);
      connectionType = ConnectionType.valueOf(properties.getProperty("connection"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (connectionType == ConnectionType.HEROKU) {
      properties.setProperty("url", System.getenv("S1"));
      properties.setProperty("user", System.getenv("S2"));
      properties.setProperty("password", System.getenv("S3"));
    }

    return properties;
  }
}
