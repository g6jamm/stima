package com.g6jamm.stima.data.repository;

/**
 * @Mohamad, Andreas
 */

import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Task;

import java.util.List;

public interface TaskRepository {

  Task createTask(Task task, int projectId) throws SystemException;

  List<Task> getTasks(int projectId) throws SystemException;

  void editTask(Task task) throws SystemException;

  void deleteTask(int taskId) throws SystemException;
}
