package com.g6jamm.stima.data.repository.mysql;

import com.g6jamm.stima.data.repository.SubProjectRepository;
import com.g6jamm.stima.data.repository.TaskRepository;
import com.g6jamm.stima.data.repository.util.DbManager;
import com.g6jamm.stima.domain.model.Project;
import com.g6jamm.stima.domain.model.ProjectComposite;
import com.g6jamm.stima.domain.model.ProjectLeaf;
import com.g6jamm.stima.domain.model.Task;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SubProjectRepositoryImpl implements SubProjectRepository {

  private final TaskRepository TASK_REPOSITORY = new TaskRepositoryImpl();

  @Override
  public List<Project> getSubProjects(int projectId) {

    List<Project> subProjects = new ArrayList<>();
    String query = "SELECT * FROM projects WHERE parent_project_id = ?";

    try {
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setInt(1, projectId);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        ProjectLeaf subProject =
            new ProjectLeaf.SubProjectBuilder()
                .subProjectId(rs.getInt("project_id"))
                .name(rs.getString("name"))
                .tasks(
                    TASK_REPOSITORY.getTasks(
                        rs.getInt("project_id"))) // TODO kan laves som innerjoin istedet
                .startDate(LocalDate.parse(rs.getString("start_date")))
                .endDate(LocalDate.parse(rs.getString("end_date")))
                .colorCode("#dc5b6e") // TODO rs.getString("color_id")
                .build();
        subProjects.add(subProject);
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage()); // TODO FIX
    }

    return subProjects;
  }

  @Override
  public Project getSubproject(int subProjectId) {

    String query = "SELECT * FROM projects WHERE project_id  = ?";

    try {
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setInt(1, subProjectId);

      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        return new ProjectLeaf.SubProjectBuilder()
            .subProjectId(rs.getInt("project_id"))
            .name(rs.getString("name"))
            .startDate(LocalDate.parse(rs.getString("start_date")))
            .endDate(LocalDate.parse(rs.getString("end_date")))
            .colorCode("#dc5b6e") // TODO add rs.getString("color_id")
            .build();
        // TODO colorcode handel,
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage()); // TODO FIX
    }

    return null;
  }

  @Override
  public ProjectLeaf createSubProject(
      String name,
      LocalDate startDate,
      LocalDate endDate,
      String projectColorParam,
      int parentProjectId) {
    // TODO change colorcode to id
    String query =
        "INSERT INTO projects(name, start_date, end_date, color_id, parent_project_id) values"
            + " (?,?,?,?,?)";

    try {
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setString(1, name);
      ps.setDate(2, Date.valueOf(startDate));
      ps.setDate(3, Date.valueOf(endDate));
      ps.setInt(4, 2); // TODO change to colorCodeId
      ps.setInt(5, parentProjectId);

      ps.execute();

    } catch (SQLException e) {
      System.out.println(e.getMessage()); // TODO FIX
    }

    return null;
  }

  @Override
  public ProjectLeaf deleteSubProject(int subProjectId) {
    return null;
  }

  @Override
  public boolean addTaskToSubProject(int subProjectId, Task task) {
    return false;
  }

  @Override
  public double getTotalHours(ProjectLeaf subProject) {
    return 0;
  }

  @Override
  public int getTotalPrice(ProjectLeaf subProject) {
    return 0;
  }

  @Override
  public void editProject(ProjectComposite subProject) {

    try {
      String query =
          "UPDATE projects SET name = ?, start_date = ?, end_date = ?, color_id = ? WHERE project_id = ?";
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setString(1, subProject.getName());
      ps.setString(2, String.valueOf(Date.valueOf(subProject.getStartDate())));
      ps.setString(3, String.valueOf(Date.valueOf(subProject.getEndDate())));
      ps.setString(4, subProject.getColorCode()); // TODO: @Jackie
      ps.setInt(5, subProject.getId());

      ps.execute();

    } catch (SQLException e) {
      e.printStackTrace(); // TODO
    }
  }

  @Override
  public void deleteProject(int projectId) {

    try {
      String query = "DELETE FROM projects WHERE project_id = ?";
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setInt(1, projectId);
      ps.execute();

    } catch (SQLException e) {
      e.printStackTrace(); // TODO
    }
  }
}
