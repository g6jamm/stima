package com.g6jamm.stima.data.repository.mysql;

import com.g6jamm.stima.data.repository.ProjectRepository;
import com.g6jamm.stima.data.repository.util.DbManager;
import com.g6jamm.stima.domain.model.Project;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryMySQLImpl implements ProjectRepository {
  @Override
  public Project createProject(Project project) {

    try {
      String query =
          "INSERT INTO projects (name, start_date, end_date, color_id, parent_project_id) VALUES "
              + "(?, ?, ?, ?, ?)";
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);

      ps.setString(1, project.getName());
      ps.setString(2, String.valueOf(Date.valueOf(project.getStartDate())));
      ps.setString(3, String.valueOf(Date.valueOf(project.getEndDate())));
      ps.setInt(4, 1); // TODO: @Jackie
      ps.setString(5, null);
      ps.execute();
    } catch (SQLException e) {
      e.printStackTrace(); // TODO
    }

    return project;
  }

  @Override
  public Project getProject(int projectId) {

    try {
      String query = "SELECT * FROM projects WHERE project_id = ?";

      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setInt(1, projectId);

      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        return new Project.ProjectBuilder()
            .projectId(projectId)
            .projectName(rs.getString("name"))
            .startDate(LocalDate.parse(rs.getString("start_date")))
            .endDate(LocalDate.parse(rs.getString("end_date")))
            .colorCode(rs.getString("color_id")) //  // TODO: @Jackie
            .build();
      }

    } catch (SQLException e) {
      e.printStackTrace(); // TODO
    }

    return null;
  }

  @Override
  public void deleteProject(int projectId) {

    try {
      String query = "DELETE FROM projects WHERE project_id = ?";
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setInt(1, projectId);
      ps.executeQuery();

    } catch (SQLException e) {
      e.printStackTrace(); // TODO
    }
  }

  @Override
  public void editProject(Project project) {

    try {
      String query =
          "UPDATE projects SET name = ?, start_date = ?, end_date = ?, color_id = ? WHERE"
              + " project_id = ?";
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setString(1, project.getName());
      ps.setString(2, String.valueOf(Date.valueOf(project.getStartDate())));
      ps.setString(3, String.valueOf(Date.valueOf(project.getEndDate())));
      ps.setString(4, project.getColorCode()); // TODO: @Jackie
      ps.setInt(5, project.getId());

      ps.execute();

    } catch (SQLException e) {
      e.printStackTrace(); // TODO
    }
  }

  @Override
  public List<Project> getProjects() {

    try {
      List<Project> projects = new ArrayList<>();
      String query = "SELECT * FROM projects WHERE parent_project_id IS NULL";
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        Project project =
            new Project.ProjectBuilder()
                .projectId(rs.getInt("project_id"))
                .projectName(rs.getString("name"))
                .startDate(LocalDate.parse(rs.getString("start_date")))
                .endDate(LocalDate.parse(rs.getString("end_date")))
                .colorCode(rs.getString("color_id")) // TODO: @Jackie
                .build();

        projects.add(project);
      }

      return projects;
    } catch (SQLException e) {
      e.printStackTrace(); // TODO
    }

    return null;
  }
}
