package com.g6jamm.stima.data.repository.mysql;

import com.g6jamm.stima.data.repository.UserRepository;
import com.g6jamm.stima.data.repository.mysql.util.DbManager;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Permission;
import com.g6jamm.stima.domain.model.ResourceType;
import com.g6jamm.stima.domain.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepositoryImpl implements UserRepository {

  /** @author Mohamad, Mathias */
  @Override
  public User login(String email, String password) throws SystemException {
    try {
      String query =
          "SELECT * "
              + "FROM users u "
              + "INNER JOIN permissions up "
              + "ON up.permission_id = u.permission_id "
              + "INNER JOIN resource_types ur "
              + "ON ur.resource_type_id = u.resource_type_id "
              + "WHERE u.email = ? "
              + "AND u.password = ?";

      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setString(1, email);
      ps.setBytes(2, password.getBytes());

      ResultSet resultSet = ps.executeQuery();

      if (resultSet.next()) {
        return new User.UserBuilder()
            .id(resultSet.getInt("user_id"))
            .firstName(resultSet.getString("first_name"))
            .lastName(resultSet.getString("last_name"))
            .email(resultSet.getString("email"))
            .password(resultSet.getString("password"))
            .resourceType(
                new ResourceType.ResourceTypeBuilder()
                    .id(resultSet.getInt("ur.resource_type_id"))
                    .name(resultSet.getString("ur.name"))
                    .build())
            .permission(
                new Permission.PermissionBuilder()
                    .id(resultSet.getInt("up.permission_id"))
                    .name(resultSet.getString("up.name"))
                    .build())
            .build();
      }
    } catch (SQLException e) {
      throw new SystemException(e);
    }
    return null;
  }

  /** @author Mohamad */
  @Override
  public User createUser(User user) throws SystemException {
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

  /** @author Mohamad */
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

  /** @author Mohamad */
  @Override
  public int getNewUserId(User user) throws SystemException {

    try {
      String query =
          "INSERT INTO users(first_name, last_name, email, password, resource_type_id,"
              + " permission_id) VALUES(?, ?, ?, ?, ?, ?)";
      PreparedStatement ps =
          DbManager.getInstance()
              .getConnection()
              .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

      ps.setString(1, user.getFirstName());
      ps.setString(2, user.getLastName());
      ps.setString(3, user.getEmail());
      ps.setBytes(4, user.getPassword().getBytes());
      ps.setInt(5, user.getResourceType().getId());
      ps.setInt(6, user.getPermission().getId());

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
}
