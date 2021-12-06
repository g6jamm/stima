package com.g6jamm.stima.data.repository.mysql;

import com.g6jamm.stima.data.repository.UserRepository;
import com.g6jamm.stima.data.repository.util.DbManager;
import com.g6jamm.stima.domain.exception.SignUpException;
import com.g6jamm.stima.domain.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {

  @Override
  public User login(String email, String password) {
    try {
      String query = "SELECT user_id FROM users WHERE email = ? AND password = ?";
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setString(1, email);
      ps.setString(2, password);

      ResultSet resultSet = ps.executeQuery();

      if (resultSet.next()) {
        return new User.UserBuilder()
            .firstName(resultSet.getString("first_name"))
            .lastName(resultSet.getString("last_name"))
            .email(resultSet.getString("email"))
            .password(resultSet.getString("password"))
            .id(resultSet.getInt("user_id"))
            .build();
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage()); // TODO
    }
    return null;
  }

  @Override
  public User createUser(User user) throws SignUpException {
    int userId = getNewUserId(user);
    return new User.UserBuilder()
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .password(user.getPassword())
        .id(userId)
        .build();
  }

  @Override
  public boolean userExists(int id) {
    try {
      String query = "SELECT * FROM users WHERE user_id = ?";
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setInt(1, id);

      return ps.executeQuery().next();

    } catch (SQLException e) {
      System.out.println(e.getMessage()); // TODO
    }
    return false;
  }

  @Override
  public int getNewUserId(User user) {

    try {
      String query = "INSERT INTO users(first_name, last_name, email, password) VALUES(?,?,?,?)";
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);

      ps.setString(1, user.getFirstName());
      ps.setString(2, user.getLastName());
      ps.setString(3, user.getEmail());
      ps.setString(4, user.getPassword());

      ResultSet resultSet = ps.getGeneratedKeys();

      if (resultSet.next()) {
        return resultSet.getInt("user_id");
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage()); // TODO
    }
    return 0;
  }

  @Override
  public User getUser(int id) {
    try {
      String query = "SELECT * FROM users WHERE user_id = ?";
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setInt(1, id);

      ResultSet resultSet = ps.executeQuery();
      if (resultSet.next()) {
        return new User.UserBuilder()
            .firstName(resultSet.getString("first_name"))
            .lastName(resultSet.getString("last_name"))
            .email(resultSet.getString("email"))
            .password(resultSet.getString("password"))
            .id(resultSet.getInt("user_id"))
            .build();
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage()); // TODO
    }
    return null;
  }
}
