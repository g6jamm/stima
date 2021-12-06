package com.g6jamm.stima.data.repository.mysql;

import com.g6jamm.stima.data.repository.TaskRepository;
import com.g6jamm.stima.data.repository.util.DbManager;
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
  public Task createTask(Task task) {
    String query =
        "INSERT INTO tasks(name, hours, resource_type, project_id, start_date, end_date)";
    // where do we get project id from?
    return null;
  }

  @Override
  public Task getTask(int task_id) {
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
              + "inner join resource_type_id r on t.resource_type_id = r.resource_type_id\n"
              + "where task_id = ?";
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
              + "inner join resource_type_id r on t.resource_type_id = r.resource_type_id\n"
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
      return result;
    } catch (SQLException e) {
      System.out.println(e.getMessage()); // TODO
    }
    return null;
  }
}
