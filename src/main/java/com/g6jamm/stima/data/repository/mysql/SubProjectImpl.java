package com.g6jamm.stima.data.repository.mysql;

import com.g6jamm.stima.data.repository.SubProjectRepository;
import com.g6jamm.stima.data.repository.util.DbManager;
import com.g6jamm.stima.domain.model.SubProject;
import com.g6jamm.stima.domain.model.Task;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class SubProjectImpl implements SubProjectRepository {


  @Override
  public List<SubProject> getSubProjects() {
    return null; //TODO add product id
  }

  @Override
  public SubProject getSubproject(int subProjectId) {

    String query = "SELECT * FROM projects WHERE project_id = ?"; //TODO add query

    try {
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setInt(1, subProjectId);

      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        return new SubProject.SubProjectBuilder()
            .subProjectId(rs.getInt("project_id"))
            .name(rs.getString("name"))
            .startDate(LocalDate.parse(rs.getString("start_date")))
            .endDate(LocalDate.parse(rs.getString("end_date")))
            .colorCode(rs.getString("color_id"))
            .build();
        //TODO colorcode handel,
        //TODO add parent project
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage()); //TODO FIX
    }

    return null;
  }

  @Override
  public SubProject createSubProject(String name, LocalDate startDate, LocalDate endDate, String projectColorParam, int parentProjectId) {
    //TODO change colorcode to id
    String query = "INSERT INTO projects(name, start_date, end_date, color_id, parent_project_id) values (?,?,?,?,?)"; // TODO add query

    try {
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setString(1, name);
      ps.setDate(2, Date.valueOf(startDate));
      ps.setDate(3, Date.valueOf(endDate));
      ps.setInt(4, 2); //TODO change to colorcordId
      ps.setInt(5, parentProjectId);

    } catch (SQLException e) {
      System.out.println(e.getMessage()); //TODO FIX
    }

    return null;
  }

  @Override
  public SubProject deleteSubProject(SubProject subProject) {
    return null;
  }

  @Override
  public boolean addTaskToSubProject(int subProjectId, Task task) {
    return false;
  }

  @Override
  public double getTotalHours(SubProject subProject) {
    return 0;
  }

  @Override
  public int getTotalPrice(SubProject subProject) {
    return 0;
  }
}
