package com.g6jamm.stima.data.repository.mysql;

import com.g6jamm.stima.data.repository.SubProjectRepository;
import com.g6jamm.stima.data.repository.TaskRepository;
import com.g6jamm.stima.data.repository.util.DbManager;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Project;
import com.g6jamm.stima.domain.model.Headproject;
import com.g6jamm.stima.domain.model.Subproject;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jackie
 */
public class SubProjectRepositoryImpl implements SubProjectRepository {

  private final TaskRepository TASK_REPOSITORY = new TaskRepositoryImpl();

  @Override
  public List<Project> getSubProjects(int projectId) throws SystemException {

    List<Project> subProjects = new ArrayList<>();

    String query = "SELECT * FROM projects WHERE parent_project_id = ?";

    try {
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setInt(1, projectId);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        Subproject subProject =
            new Subproject.SubProjectBuilder()
                .subProjectId(rs.getInt("project_id"))
                .name(rs.getString("name"))
                .tasks(TASK_REPOSITORY.getTasks(rs.getInt("project_id")))
                // Vi kan spare kald til databasen ved at bygge et
                // task-objekt i metoden. Dette er bevidst fravalgt, da det
                // vil gøre koden væsentlig mindre vedligeholdes venlig.
                .startDate(LocalDate.parse(rs.getString("start_date")))
                .endDate(LocalDate.parse(rs.getString("end_date")))
                .colorCode(rs.getString("colorscode"))
                .build();
        subProjects.add(subProject);
      }

    } catch (SQLException e) {
      throw new SystemException(e);
    }

    return subProjects;
  }

  @Override
  public Subproject createSubProject(
      String name,
      LocalDate startDate,
      LocalDate endDate,
      String projectColorParam,
      int parentProjectId)
      throws SystemException {
    String query =
        "INSERT INTO projects(name, start_date, end_date, colorscode, parent_project_id) "
            + "VALUES (?, ?, ?, ?, ?)";

    try {
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setString(1, name);
      ps.setDate(2, Date.valueOf(startDate));
      ps.setDate(3, Date.valueOf(endDate));
      ps.setString(4, projectColorParam);
      ps.setInt(5, parentProjectId);

      ps.execute();

    } catch (SQLException e) {
      throw new SystemException(e);
    }

    return null;
  }

  @Override
  public void editProject(Headproject subProject) throws SystemException {

    try {
      String query =
          "UPDATE projects "
              + "SET name = ?, start_date = ?, end_date = ?, colorscode = ? "
              + "WHERE project_id = ?";

      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setString(1, subProject.getName());
      ps.setString(2, String.valueOf(Date.valueOf(subProject.getStartDate())));
      ps.setString(3, String.valueOf(Date.valueOf(subProject.getEndDate())));
      ps.setString(4, subProject.getColorCode());
      ps.setInt(5, subProject.getId());

      ps.execute();

    } catch (SQLException e) {
      throw new SystemException(e);
    }
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
}
