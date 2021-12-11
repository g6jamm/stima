package com.g6jamm.stima.data.repository.mysql;

import com.g6jamm.stima.data.repository.TaskRepository;
import com.g6jamm.stima.data.repository.util.DbManager;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.ResourceType;
import com.g6jamm.stima.domain.model.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {
  @Override
  public Task createTask(Task task, int projectId) throws SystemException {

    try {
      String query =
          "INSERT INTO tasks(name, hours, resourdaqce_type_id, project_id, start_date, end_date)"
              + " VALUES(?,?,?,?,?,?)";
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);

      ps.setString(1, task.getName());
      ps.setDouble(2, task.getHours());
      ps.setInt(3, task.getResourceType().getId());
      ps.setInt(4, projectId);
      ps.setString(5, task.getStartDate().toString());
      ps.setString(6, task.getEndDate().toString());

      ps.executeUpdate();
      return task;
    } catch (SQLException e) {
      throw new SystemException("Please contact system administrator");
    }
  }

  @Override
  public Task getTask(int task_id) {
    try {
      String query =
          "SELECT task_id"
              + ",t.name AS task_name"
              + ", hours"
              + ", r.resource_type_id"
              + ", project_id"
              + ", start_date"
              + ", end_date"
              + ", price_per_hour"
              + ", r.name AS resource_name"
              + "FROM tasks t"
              + "INNER JOIN resource_type r ON t.resource_type_id = r.resource_type_id"
              + "WHERE task_id = ?";
      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setInt(1, task_id);
      ResultSet resultSet = ps.executeQuery();

      if (resultSet.next()) {

        ResourceType resourceType =
            new ResourceType.ResourceTypeBuilder()
                .name(resultSet.getString("resource_name"))
                .id(resultSet.getInt("resource_type_id"))
                .pricePrHour(resultSet.getInt("price_per_hour"))
                .build();

        return new Task.TaskBuilder()
            .id(resultSet.getInt("task_id"))
            .name(resultSet.getString("task_name"))
            .startDate(LocalDate.parse(resultSet.getString("start_date")))
            .endDate(LocalDate.parse(resultSet.getString("end_date")))
            .hours(resultSet.getDouble("hours"))
            .resourceType(resourceType)
            .build();
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage()); // TODO
    }
    return null;
  }

  @Override
  public List<Task> getTasks(int projectId) {
    List<Task> result = new ArrayList<>();

    try {
      String query =
          "SELECT task_id"
              + ",t.name as task_name"
              + ", hours"
              + ", r.resource_type_id"
              + ", project_id"
              + ", start_date"
              + ", end_date"
              + ", price_per_hour"
              + ", r.name as resource_name \n"
              + "FROM tasks t\n"
              + "inner join resource_type r on t.resource_type_id = r.resource_type_id\n"
              + "where project_id = ?";

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
      System.out.println(e.getMessage()); // TODO
    }
    return result;
  }
}
