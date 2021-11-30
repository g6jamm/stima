package com.g6jamm.stima.data.repository.mock;

import com.g6jamm.stima.data.repository.TaskRepository;
import com.g6jamm.stima.domain.model.Role;
import com.g6jamm.stima.domain.model.Task;
import com.g6jamm.stima.domain.model.User;

import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryStub implements TaskRepository {

  public static List<Task> taskListStub = new ArrayList<>();
  private int generatedIdStub = taskListStub.size();

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

    taskListStub.add(task);
    return task;
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
