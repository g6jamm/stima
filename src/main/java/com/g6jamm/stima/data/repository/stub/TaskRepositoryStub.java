package com.g6jamm.stima.data.repository.stub;

import com.g6jamm.stima.data.repository.ResourceTypeRepository;
import com.g6jamm.stima.data.repository.TaskRepository;
import com.g6jamm.stima.domain.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryStub implements TaskRepository {

  private static ResourceTypeRepository resourceTypeRepository = new ResourceTypeRepositoryStub();
  private static List<Task> taskListStub = new ArrayList<>();

  public TaskRepositoryStub() {
    if (taskListStub.isEmpty()) {
      Task task =
          new Task.TaskBuilder()
              .name("Task one")
              .resourceType(resourceTypeRepository.getResourceTypes().get(1))
              .hours(5)
              .startDate(LocalDate.parse("1990-01-01"))
              .endDate(LocalDate.parse("1990-01-02"))
              .id(taskListStub.size() + 1)
              .build();

      Task task2 =
          new Task.TaskBuilder()
              .name("Task one")
              .resourceType(resourceTypeRepository.getResourceTypes().get(2))
              .hours(5)
              .startDate(LocalDate.parse("1990-01-01"))
              .endDate(LocalDate.parse("1990-01-02"))
              .id(taskListStub.size() + 1)
              .build();

      taskListStub.add(task);
      taskListStub.add(task2);
    }
  }

  @Override
  public Task createTask(Task task, int projectId) {
    task =
        new Task.TaskBuilder()
            .name(task.getName())
            .resourceType(task.getResourceType()) // TODO Lookup value from form
            .hours(task.getHours())
            .startDate(task.getStartDate())
            .endDate(task.getEndDate())
            .id(taskListStub.size() + 1)
            .build();

    return task;
  }

  @Override
  public List<Task> getTasks(int projectId) {
    return taskListStub;
  }

  @Override
  public void editTask(Task task) {}

  @Override
  public Task getTask(int task_id) {
    Task result = null;
    for (Task t : taskListStub) {
      if (t.getId() == task_id) {
        result = t;
      }
    }
    return result;
  }
}
