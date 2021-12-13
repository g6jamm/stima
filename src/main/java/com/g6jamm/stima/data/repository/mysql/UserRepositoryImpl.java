package com.g6jamm.stima.data.repository.mysql;

import com.g6jamm.stima.data.repository.UserRepository;
import com.g6jamm.stima.data.repository.util.DbManager;
import com.g6jamm.stima.domain.exception.SignUpException;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepositoryImpl implements UserRepository {

  @Override
  public User login(String email, String password) throws SystemException {
    try {
      String query = "SELECT * FROM users WHERE email = ? AND password = ?";
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setString(1, email);
      ps.setBytes(2, password.getBytes());

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
      throw new SystemException(e);
    }
    return null;
  }

  @Override
  public User createUser(User user) throws SignUpException, SystemException {
    int userId = getNewUserId(user);
    return new User.UserBuilder()
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .password(user.getPassword())
        .resourceType(user.getResourceType())
        .permission(user.getPermission())
        .id(userId)
        .build();
  }

  @Override
  public boolean userExists(int id) throws SystemException {
    try {
      String query = "SELECT * FROM users WHERE user_id = ?";
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setInt(1, id);

      return ps.executeQuery().next();

    } catch (SQLException e) {

      throw new SystemException(e);
    }
  }

  @Override
  public int getNewUserId(User user) throws SystemException {

    try {
      String query =
          "INSERT INTO users(first_name, last_name, email, password, resource_type_id,"
              + " permission_id) VALUES(?,?,?,?,?,?)";
      PreparedStatement ps =
          DbManager.getInstance()
              .getConnection()
              .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

      ps.setString(1, user.getFirstName());
      ps.setString(2, user.getLastName());
      ps.setString(3, user.getEmail());
      ps.setBytes(4, user.getPassword().getBytes());
      ps.setInt(5, user.getResourceType().getId()); // implementeres i senere iteration
      ps.setInt(6, user.getPermission().getId()); // implementeres i senere iteration

      ps.executeUpdate();
      ResultSet resultSet = ps.getGeneratedKeys();

      if (resultSet.next()) {
        return resultSet.getInt(1);
      }

    } catch (SQLException e) {
      throw new SystemException(e);
    }
    return 0;
  }

  @Override
  public User getUser(int id) throws SystemException {
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
      throw new SystemException(e);
    }
    return null;
  }
}
