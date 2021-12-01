package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.ResourceTypeRepository;
import com.g6jamm.stima.data.repository.TaskRepository;
import com.g6jamm.stima.domain.model.ResourceType;
import com.g6jamm.stima.domain.model.Task;

import java.time.LocalDate;
import java.util.List;

public class TaskService {

  private TaskRepository taskRepository;
  private ResourceTypeRepository resourceTypeRepository;

  public TaskService(TaskRepository taskRepository, ResourceTypeRepository resourceTypeRepository) {
    this.taskRepository = taskRepository;
    this.resourceTypeRepository = resourceTypeRepository;
  }

  public Task createtask(
      String taskName, double hours, String resourceType, String startDate, String endDate) {
    Task newTask =
        new Task.TaskBuilder()
            .name(taskName)
            .hours(hours)
            .startDate(convertStringToDate(startDate))
            .endDate(convertStringToDate(endDate))
            .build();

    return taskRepository.createTask(newTask);
  }

  private LocalDate convertStringToDate(String stringDate) {
    return LocalDate.parse(stringDate);
  }

  public List<Task> getTasks() {
    return taskRepository.getTasks();
  }

  public List<ResourceType> getResourceTypes(){
    return resourceTypeRepository.getResourceTypes();
  }

}
