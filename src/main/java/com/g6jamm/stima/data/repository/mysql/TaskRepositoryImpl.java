package com.g6jamm.stima.data.repository.mysql;

import com.g6jamm.stima.data.repository.TaskRepository;
import com.g6jamm.stima.data.repository.mysql.util.DbManager;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.ResourceType;
import com.g6jamm.stima.domain.model.Task;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {

  /** @author Andreas */
  @Override
  public Task createTask(Task task, int projectId) throws SystemException {

    try {
      String query =
          "INSERT INTO tasks(name, hours, resource_type_id, project_id, start_date, end_date) "
              + "VALUES(?, ?, ?, ?, ?, ?)";
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);

      ps.setString(1, task.getName());
      ps.setDouble(2, task.getHours());
      ps.setInt(3, task.getResourceType().getId());
      ps.setInt(4, projectId);
      ps.setDate(5, Date.valueOf(task.getStartDate()));
      ps.setDate(6, Date.valueOf(task.getEndDate()));

      ps.executeUpdate();
      return task;
    } catch (SQLException e) {
      throw new SystemException(e);
    }
  }

  /**
   * Returns a list of Task objects from the database, by a given project id.
   *
   * @auther Andreas, Mohamad, Mathias
   */
  @Override
  public List<Task> getTasks(int projectId) throws SystemException {
    List<Task> result = new ArrayList<>();

    try {
      String query =
          "SELECT task_id, "
              + "t.name as task_name, "
              + "hours, "
              + "r.resource_type_id, "
              + "project_id, "
              + "start_date, "
              + "end_date, "
              + "price_per_hour, "
              + "r.name AS resource_name "
              + "FROM tasks t "
              + "INNER JOIN resource_types r "
              + "ON t.resource_type_id = r.resource_type_id "
              + "WHERE project_id = ?";

      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setInt(1, projectId);
      ResultSet resultSet = ps.executeQuery();

      while (resultSet.next()) {
        ResourceType resourceType =
            new ResourceType.ResourceTypeBuilder()
                .name(resultSet.getString("resource_name"))
                .id(resultSet.getInt("resource_type_id"))
                .pricePrHour(resultSet.getInt("price_per_hour"))
                .build();

        result.add(
            new Task.TaskBuilder()
                .id(resultSet.getInt("task_id"))
                .name(resultSet.getString("task_name"))
                .startDate(LocalDate.parse(resultSet.getString("start_date")))
                .endDate(LocalDate.parse(resultSet.getString("end_date")))
                .hours(resultSet.getDouble("hours"))
                .resourceType(resourceType)
                .build());
      }
    } catch (SQLException e) {
      throw new SystemException(e);
    }

    return result;
  }

  /**
   * Edit task in the database for a given task.
   *
   * @auther Mathias
   */
  @Override
  public void editTask(Task task) throws SystemException {
    try {
      String query =
          "UPDATE tasks "
              + "SET name = ?, hours = ?, resource_type_id = ?, start_date = ?, end_date = ? "
              + "WHERE task_id = ?";

      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setString(1, task.getName());
      ps.setDouble(2, task.getHours());
      ps.setInt(3, task.getResourceType().getId());
      ps.setString(4, String.valueOf(Date.valueOf(task.getStartDate())));
      ps.setString(5, String.valueOf(Date.valueOf(task.getEndDate())));
      ps.setInt(6, task.getId());

      ps.execute();

    } catch (SQLException e) {
      throw new SystemException(e);
    }
  }

  /**
   * Delete task in the database by a given task id.
   *
   * @auther Mathias
   */
  @Override
  public void deleteTask(int taskId) throws SystemException {

    try {
      String query = "DELETE FROM tasks WHERE task_id = ?";
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setInt(1, taskId);
      ps.execute();

    } catch (SQLException e) {
      throw new SystemException(e);
    }
  }
}
