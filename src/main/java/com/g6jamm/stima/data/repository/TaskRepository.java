package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Task;

import java.util.List;

public interface TaskRepository {

  Task createTask(Task task, int projectId) throws SystemException;

  Task getTask(int task_id);

  List<Task> getTasks(int projectId);
}
