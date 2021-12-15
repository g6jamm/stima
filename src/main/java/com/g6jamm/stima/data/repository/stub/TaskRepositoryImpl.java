package com.g6jamm.stima.data.repository.stub;

import com.g6jamm.stima.data.repository.ResourceTypeRepository;
import com.g6jamm.stima.data.repository.TaskRepository;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {

  private static final ResourceTypeRepository RESOURCE_TYPE_REPOSITORY =
      new ResourceTypeRepositoryImpl();
  private static final List<Task> TASK_LIST = new ArrayList<>();

  public TaskRepositoryImpl() throws SystemException {
    if (TASK_LIST.isEmpty()) {
      Task task =
          new Task.TaskBuilder()
              .name("Task one")
              .resourceType(RESOURCE_TYPE_REPOSITORY.getResourceTypes().get(1))
              .hours(5)
              .startDate(LocalDate.parse("1990-01-01"))
              .endDate(LocalDate.parse("1990-01-02"))
              .id(TASK_LIST.size() + 1)
              .build();

      Task task2 =
          new Task.TaskBuilder()
              .name("Task one")
              .resourceType(RESOURCE_TYPE_REPOSITORY.getResourceTypes().get(2))
              .hours(5)
              .startDate(LocalDate.parse("1990-01-01"))
              .endDate(LocalDate.parse("1990-01-02"))
              .id(TASK_LIST.size() + 1)
              .build();

      TASK_LIST.add(task);
      TASK_LIST.add(task2);
    }
  }

  @Override
  public Task createTask(Task task, int projectId) throws SystemException {
    return new Task.TaskBuilder()
        .name(task.getName())
        .resourceType(task.getResourceType())
        .hours(task.getHours())
        .startDate(task.getStartDate())
        .endDate(task.getEndDate())
        .id(TASK_LIST.size() + 1)
        .build();
  }

  @Override
  public List<Task> getTasks(int projectId) throws SystemException {
    return TASK_LIST;
  }

  @Override
  public void editTask(Task task) throws SystemException {}

  @Override
  public void deleteTask(int taskId) throws SystemException {}

  @Override
  public Task getTask(int task_id) throws SystemException {
    Task result = null;
    for (Task t : TASK_LIST) {
      if (t.getId() == task_id) {
        result = t;
      }
    }
    return result;
  }
}
