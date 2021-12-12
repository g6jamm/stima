package com.g6jamm.stima.data.repository.mysql;

import com.g6jamm.stima.data.repository.ProjectRepository;
import com.g6jamm.stima.data.repository.SubProjectRepository;
import com.g6jamm.stima.data.repository.TaskRepository;
import com.g6jamm.stima.data.repository.util.DbManager;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Project;
import com.g6jamm.stima.domain.model.ProjectComposite;
import com.g6jamm.stima.domain.model.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryMySQLImpl implements ProjectRepository {

  private final TaskRepository TASK_REPOSITORY = new TaskRepositoryImpl();
  private final SubProjectRepository SUBPROJECT_REPOSITORY = new SubProjectRepositoryImpl();

  @Override
  public ProjectComposite createProject(ProjectComposite project, User user)
      throws SystemException {

    try {
      String query =
          "INSERT INTO projects (name, start_date, end_date, colorscode, parent_project_id) VALUES "
              + "(?, ?, ?, ?, ?)";
      PreparedStatement ps =
          DbManager.getInstance()
              .getConnection()
              .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

      ps.setString(1, project.getName());
      ps.setString(2, String.valueOf(Date.valueOf(project.getStartDate())));
      ps.setString(3, String.valueOf(Date.valueOf(project.getEndDate())));
      ps.setString(4, project.getColorCode());
      ps.setString(5, null);

      ps.executeUpdate();

      ResultSet gk = ps.getGeneratedKeys();
      if (gk.next()) {
        project =
            new ProjectComposite.ProjectBuilder()
                .projectId(gk.getInt(1))
                .projectName(project.getName())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .colorCode(project.getColorCode())
                .tasks(project.getTasks())
                .subProjects(project.getSubProjects())
                .build();

        linkProjectAndUser(project, user);
      }

    } catch (SQLException e) {
      throw new SystemException(e);
    }

    return project;
  }

  @Override
  public ProjectComposite getProject(int projectId) throws SystemException {

    try {
      String query = "SELECT * FROM projects WHERE project_id = ? AND parent_project_id is NULL";

      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setInt(1, projectId);

      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        return new ProjectComposite.ProjectBuilder()
            .projectId(projectId)
            .projectName(rs.getString("name"))
            .startDate(LocalDate.parse(rs.getString("start_date")))
            .endDate(LocalDate.parse(rs.getString("end_date")))
            .colorCode(rs.getString("colorscode"))
            .tasks(
                TASK_REPOSITORY.getTasks(
                    rs.getInt("project_id"))) // TODO kan laves som innerjoin istedet
            .subProjects(
                SUBPROJECT_REPOSITORY.getSubProjects(
                    rs.getInt("project_id"))) // TODO kan laves som innerjoin istedet
            .build();
      }

    } catch (SQLException e) {
      throw new SystemException(e);
    }

    return null;
  }

  @Override
  public void deleteProject(int projectId) throws SystemException {

    try {
      String query = "DELETE FROM projects WHERE project_id = ?";
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setInt(1, projectId);
      ps.execute();

    } catch (SQLException e) {
      throw new SystemException(e);
    }
  }

  @Override
  public void editProject(ProjectComposite project) throws SystemException {

    try {
      String query =
          "UPDATE projects SET name = ?, start_date = ?, end_date = ?, colorscode = ? WHERE"
              + " project_id = ?";
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setString(1, project.getName());
      ps.setString(2, String.valueOf(Date.valueOf(project.getStartDate())));
      ps.setString(3, String.valueOf(Date.valueOf(project.getEndDate())));
      ps.setString(4, project.getColorCode());
      ps.setInt(5, project.getId());

      ps.execute();

    } catch (SQLException e) {
      throw new SystemException(e);
    }
  }

  @Override
  public List<ProjectComposite> getProjects(User user) throws SystemException {

    List<ProjectComposite> projects = new ArrayList<>();
    try {

      String query =
          "SELECT * FROM project_users pu INNER JOIN projects p ON pu.project_id = p.project_id"
              + " WHERE user_id = ?";
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
                .colorCode(rs.getString("colorscode"))
                .build();

        projects.add(project);
      }

    } catch (SQLException e) {
      throw new SystemException(e);
    }

    return projects;
  }

  private boolean linkProjectAndUser(Project project, User user) throws SQLException {
    String query =
        "INSERT INTO project_users (project_id, user_id, role_id) VALUES (?, ?, 1)"; // 1 is for
    // role_id;
    PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
    ps.setInt(1, project.getId());
    ps.setInt(2, user.getId());
    return ps.execute();
  }
}
