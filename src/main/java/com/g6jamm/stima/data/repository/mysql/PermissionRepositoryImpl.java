package com.g6jamm.stima.data.repository.mysql;

import com.g6jamm.stima.data.repository.PermissionRepository;
import com.g6jamm.stima.data.repository.util.DbManager;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Permission;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** @author Mohamad */
public class PermissionRepositoryImpl implements PermissionRepository {
  @Override
  public List<Permission> getPermissions() throws SystemException {
    List<Permission> result = new ArrayList<>();

    try {
      String query = "SELECT * FROM permissions";
      ResultSet rs = DbManager.getInstance().getConnection().prepareStatement(query).executeQuery();

      while (rs.next()) {

        Permission permission =
            new Permission.PermissionBuilder()
                .id(rs.getInt("permission_id"))
                .name(rs.getString("name"))
                .build();

        result.add(permission);
      }

      return result;

    } catch (SQLException e) {
      throw new SystemException(e);
    }
  }

  @Override
  public Permission getPermission(String permissionName) throws SystemException {
    try {
      String query = "SELECT * FROM permissions WHERE name = ?";

      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setString(1, permissionName);
      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        return new Permission.PermissionBuilder()
            .id(rs.getInt("permission_id"))
            .name(rs.getString("name"))
            .build();
      }
    } catch (SQLException e) {
      throw new SystemException(e);
    }
    return null;
  }
}
