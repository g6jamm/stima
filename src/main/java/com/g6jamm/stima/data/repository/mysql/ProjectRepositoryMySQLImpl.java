package com.g6jamm.stima.data.repository.mysql;

import com.g6jamm.stima.data.repository.ProjectRepository;
import com.g6jamm.stima.data.repository.SubProjectRepository;
import com.g6jamm.stima.data.repository.TaskRepository;
import com.g6jamm.stima.data.repository.util.DbManager;
import com.g6jamm.stima.domain.model.ProjectComposite;
import com.g6jamm.stima.domain.model.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryMySQLImpl implements ProjectRepository {

  private final TaskRepository TASK_REPOSITORY = new TaskRepositoryImpl();
  private final SubProjectRepository SUBPROJECT_REPOSITORY = new SubProjectRepositoryImpl();

  @Override
  public ProjectComposite createProject(ProjectComposite project) {

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
  public ProjectComposite getProject(int projectId) {

    try {
      String query = "SELECT * FROM projects WHERE project_id = ? AND project_parent_id is NULL";

      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setInt(1, projectId);

      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        return new ProjectComposite.ProjectBuilder()
            .projectId(projectId)
            .projectName(rs.getString("name"))
            .startDate(LocalDate.parse(rs.getString("start_date")))
            .endDate(LocalDate.parse(rs.getString("end_date")))
            .colorCode(rs.getString("color_id")) //  // TODO: @Jackie
            .tasks(
                TASK_REPOSITORY.getTasks(
                    rs.getInt("project_id"))) // TODO kan laves som innerjoin istedet
            .subProjects(
                SUBPROJECT_REPOSITORY.getSubProjects(
                    rs.getInt("project_id"))) // TODO kan laves som innerjoin istedet
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
  public void editProject(ProjectComposite project) {

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
  public List<ProjectComposite> getProjects(User user) {

    List<ProjectComposite> projects = new ArrayList<>();
    try {

      String query = "SELECT * FROM project_users pu INNER JOIN projects p ON pu.project_id = p.project_id WHERE user_id = ?";
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setInt(1, user.getId());
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        ProjectComposite project =
            new ProjectComposite.ProjectBuilder()
                .projectId(rs.getInt("project_id"))
                .projectName(rs.getString("name"))
                .startDate(LocalDate.parse(rs.getString("start_date")))
                .endDate(LocalDate.parse(rs.getString("end_date")))
                .tasks(
                    TASK_REPOSITORY.getTasks(
                        rs.getInt("project_id"))) // TODO kan laves som innerjoin istedet
                .subProjects(
                    SUBPROJECT_REPOSITORY.getSubProjects(
                        rs.getInt("project_id"))) // TODO kan laves som innerjoin istedet
                .colorCode(rs.getString("color_id")) // TODO: @Jackie
                .build();

        projects.add(project);
      }

    } catch (SQLException e) {
      e.printStackTrace(); // TODO
    }

    return projects;
  }
}
