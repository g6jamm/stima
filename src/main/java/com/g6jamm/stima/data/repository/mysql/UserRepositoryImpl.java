package com.g6jamm.stima.data.repository.mysql;

import com.g6jamm.stima.data.repository.UserRepository;
import com.g6jamm.stima.data.repository.util.DbManager;
import com.g6jamm.stima.domain.exception.LoginException;
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

      if (resultSet.next()){
        return new User.UserBuilder()
            .firstName(resultSet.getString("first_name"))
            .lastName(resultSet.getString("last_name"))
            .email(resultSet.getString("email"))
            .password(resultSet.getString("password"))
            .id(resultSet.getInt("user_id"))
            .build();
      }
    }catch (SQLException e){
      System.out.println(e.getMessage());
    }
    return null;
  }

  @Override
  public User createUser(User user) throws SignUpException {
    return null;
  }

  @Override
  public boolean userExists(int id) {
    return false;
  }

  @Override
  public int getNewUserId(User user) {
    return 0;
  }

  @Override
  public User getUser(int id) {
    return null;
  }
}
