package com.g6jamm.stima.data.repository.mysql;

import com.g6jamm.stima.data.repository.ProjectColorRepository;
import com.g6jamm.stima.data.repository.mysql.util.DbManager;
import com.g6jamm.stima.domain.exception.SystemException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/** @author Mathias */
public class ProjectColorImpl implements ProjectColorRepository {
  public Map<String, String> getProjectColors() throws SystemException {

    try {
      String query = "SELECT * FROM colors";

      ResultSet rs = DbManager.getInstance().getConnection().prepareStatement(query).executeQuery();

      Map<String, String> colors = new HashMap<>();

      while (rs.next()) {
        colors.put(rs.getString("name"), rs.getString("code"));
      }

      return colors;
    } catch (SQLException e) {
      throw new SystemException(e);
    }
  }
}
