package com.g6jamm.stima.data.repository.mock;

import com.g6jamm.stima.data.repository.TaskRepository;
import com.g6jamm.stima.domain.model.Role;
import com.g6jamm.stima.domain.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryStub implements TaskRepository {

  public static List<Task> taskListStub = new ArrayList<>();
  private int generatedIdStub = taskListStub.size();

  public TaskRepositoryStub() {

    Task task =
        new Task.TaskBuilder()
            .name("Task one")
            .role(new Role())
            .price(200)
            .hours(5)
            .startDate(LocalDate.parse("1990-01-01"))
            .endDate(LocalDate.parse("1990-01-02"))
            .id(generatedIdStub)
            .build();

    Task task2 =
        new Task.TaskBuilder()
            .name("Task one")
            .role(new Role())
            .price(200)
            .hours(5)
            .startDate(LocalDate.parse("1990-01-01"))
            .endDate(LocalDate.parse("1990-01-02"))
            .id(generatedIdStub)
            .build();

    taskListStub.add(task);
    taskListStub.add(task2);
  }

  @Override
  public Task createTask(Task task) {
    task =
        new Task.TaskBuilder()
            .name(task.getName())
            .role(new Role())
            .price(task.getPrice())
            .hours(task.getHours())
            .startDate(task.getStartDate())
            .endDate(task.getEndDate())
            .id(generatedIdStub)
            .build();

    taskListStub.add(task);
    return task;
  }

  @Override
  public List<Task> getTasks() {

    List<Task> tasks = new ArrayList<>();

    tasks.add(
        new Task.TaskBuilder()
            .id(1)
            .name("task example")
            .role(new Role())
            .price(5000)
            .hours(8)
            .startDate(LocalDate.of(2021, 1, 1))
            .endDate(LocalDate.of(2021, 2, 1))
            .build());

    tasks.add(
        new Task.TaskBuilder()
            .id(2)
            .name("task example2")
            .role(new Role())
            .price(5000)
            .hours(8)
            .startDate(LocalDate.of(2021, 1, 1))
            .endDate(LocalDate.of(2021, 2, 1))
            .build());

    return tasks;
  }

  @Override
  public Task getTask(int task_id) {
    Task result = null;
    for (Task t : taskListStub) {
      if (t.getID() == task_id) {
        result = t;
      }
    }
    return result;
  }
}
