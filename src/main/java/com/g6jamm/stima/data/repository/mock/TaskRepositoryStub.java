package com.g6jamm.stima.data.repository.mock;

import com.g6jamm.stima.data.repository.TaskRepository;
import com.g6jamm.stima.domain.model.Role;
import com.g6jamm.stima.domain.model.Task;

public class TaskRepositoryStub implements TaskRepository {
  @Override
  public Task createTask(Task task) {
    task =
        new Task.TaskBuilder()
            .name(task.getName())
            .role(new Role())
            .price(task.getPrice())
            .hours(task.getHours())
            .startDate(task.getSTART_DATE())
            .endDate(task.getEND_DATE())
            .id(1)
            .build();
    return task;
  }

  @Override
  public Task getTask(int task_id) {
    return null;
  }
}
