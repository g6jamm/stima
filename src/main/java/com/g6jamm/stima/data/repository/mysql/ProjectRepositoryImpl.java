package com.g6jamm.stima.data.repository.mysql;

import com.g6jamm.stima.data.repository.ProjectRepository;
import com.g6jamm.stima.data.repository.SubProjectRepository;
import com.g6jamm.stima.data.repository.TaskRepository;
import com.g6jamm.stima.data.repository.mysql.util.DbManager;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Headproject;
import com.g6jamm.stima.domain.model.Project;
import com.g6jamm.stima.domain.model.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** @author Mathias */
public class ProjectRepositoryImpl implements ProjectRepository {

  private final TaskRepository TASK_REPOSITORY = new TaskRepositoryImpl();
  private final SubProjectRepository SUBPROJECT_REPOSITORY = new SubProjectRepositoryImpl();

  /**
   * Create a new project and store it in the database.
   *
   * @author Mathias
   */
  @Override
  public Headproject createProject(Headproject project, User user) throws SystemException {

    try {
      String query =
          "INSERT INTO projects (name, start_date, end_date, colorscode, parent_project_id) "
              + "VALUES (?, ?, ?, ?, ?)";
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
            new Headproject.ProjectBuilder()
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

  /**
   * Delete project from the database by project id.
   *
   * @author Mathias
   */
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

  /**
   * Edit project in database by project object.
   *
   * @author Mathias
   */
  @Override
  public void editProject(Headproject project) throws SystemException {

    try {
      String query =
          "UPDATE projects "
              + "SET name = ?, start_date = ?, end_date = ?, colorscode = ? "
              + "WHERE project_id = ?";

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

  /**
   * Get a list of project of owned by a user.
   *
   * @author Mathias
   */
  @Override
  public List<Headproject> getProjects(User user) throws SystemException {

    List<Headproject> projects = new ArrayList<>();
    try {

      String query =
          "SELECT * "
              + "FROM project_users pu "
              + "INNER JOIN projects p "
              + "ON pu.project_id = p.project_id "
              + "WHERE user_id = ?";

      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setInt(1, user.getId());
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        Headproject project =
            new Headproject.ProjectBuilder()
                .projectId(rs.getInt("project_id"))
                .projectName(rs.getString("name"))
                .startDate(LocalDate.parse(rs.getString("start_date")))
                .endDate(LocalDate.parse(rs.getString("end_date")))
                .tasks(TASK_REPOSITORY.getTasks(rs.getInt("project_id")))
                .subProjects(SUBPROJECT_REPOSITORY.getSubProjects(rs.getInt("project_id")))
                .colorCode(rs.getString("colorscode"))
                .build();

        projects.add(project);
      }

    } catch (SQLException e) {
      throw new SystemException(e);
    }

    return projects;
  }

  /** @author Andreas */
  private boolean linkProjectAndUser(Project project, User user) throws SQLException {
    String query = "INSERT INTO project_users (project_id, user_id, role_id) VALUES (?, ?, 1)";

    PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
    ps.setInt(1, project.getId());
    ps.setInt(2, user.getId());
    return ps.execute();
  }
}
