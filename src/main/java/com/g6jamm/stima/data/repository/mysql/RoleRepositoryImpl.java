package com.g6jamm.stima.data.repository.mysql;

import com.g6jamm.stima.data.repository.RoleRepository;
import com.g6jamm.stima.data.repository.util.DbManager;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** @author Mohamad */
public class RoleRepositoryImpl implements RoleRepository {

  @Override
  public List<Role> getRoles() throws SystemException {
    List<Role> result = new ArrayList<>();

    try {
      String query = "SELECT * FROM roles";
      ResultSet rs = DbManager.getInstance().getConnection().prepareStatement(query).executeQuery();

      while (rs.next()) {

        Role role =
            new Role.RoleBuilder().id(rs.getInt("role_id")).name(rs.getString("name")).build();

        result.add(role);
      }

      return result;

    } catch (SQLException e) {
      throw new SystemException(e);
    }
  }

  @Override
  public Role getRole(String roleName) throws SystemException {
    try {
      String query = "SELECT * FROM roles WHERE name = ?";

      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setString(1, roleName);
      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        return new Role.RoleBuilder().id(rs.getInt("role_id")).name(rs.getString("name")).build();
      }
    } catch (SQLException e) {
      throw new SystemException(e);
    }
    return null;
  }
}
