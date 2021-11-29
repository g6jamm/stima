package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.TaskRepository;
import com.g6jamm.stima.domain.model.Task;

public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }


    public Task createtask(String taskName){
        Task newTask = new Task.TaskBuilder().name(taskName).build();

        return taskRepository.createTask(newTask);
    }



}
