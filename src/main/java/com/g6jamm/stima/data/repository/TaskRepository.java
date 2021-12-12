package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.model.Task;

import java.util.List;

public interface TaskRepository {

  Task createTask(Task task, int projectId);

  Task getTask(int task_id);

  List<Task> getTasks(int projectId);

  void editTask(Task task);
}
