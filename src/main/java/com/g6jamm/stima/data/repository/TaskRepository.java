package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.model.Task;

public interface TaskRepository {

    Task createTask(Task task);

    Task getTask(int task_id);
}
