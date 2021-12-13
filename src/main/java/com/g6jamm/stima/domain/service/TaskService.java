package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.ResourceTypeRepository;
import com.g6jamm.stima.data.repository.TaskRepository;
import com.g6jamm.stima.domain.exception.ResourceTypeNotFoundException;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.exception.TaskCreationException;
import com.g6jamm.stima.domain.model.ResourceType;
import com.g6jamm.stima.domain.model.Task;

import java.time.LocalDate;
import java.util.List;

public class TaskService {

  private final TaskRepository TASK_REPOSITORY;
  private final ResourceTypeRepository RESOURCE_TYPE_REPOSITORY;

  public TaskService(TaskRepository taskRepository, ResourceTypeRepository resourceTypeRepository) {
    this.TASK_REPOSITORY = taskRepository;
    this.RESOURCE_TYPE_REPOSITORY = resourceTypeRepository;
  }

  public Task createtask(
      String taskName,
      double hours,
      String resourceType,
      String startDate,
      String endDate,
      int projectId)
      throws TaskCreationException, SystemException {

    Task newTask =
        new Task.TaskBuilder()
            .name(taskName)
            .hours(hours)
            .resourceType(findResourceTypeByName(resourceType))
            .startDate(convertStringToDate(startDate))
            .endDate(convertStringToDate(endDate))
            .build();
    return TASK_REPOSITORY.createTask(newTask, projectId);
  }

  private LocalDate convertStringToDate(String stringDate) {
    return LocalDate.parse(stringDate);
  }

  public List<Task> getTasks(int projectId) throws SystemException {
    return TASK_REPOSITORY.getTasks(projectId);
  }

  public List<ResourceType> getResourceTypes() throws SystemException {
    return RESOURCE_TYPE_REPOSITORY.getResourceTypes();
  }

  private ResourceType findResourceTypeByName(String resourceTypeName)
      throws TaskCreationException {
    try {
      ResourceType resourceType = RESOURCE_TYPE_REPOSITORY.findByName(resourceTypeName);
      return resourceType;
    } catch (ResourceTypeNotFoundException e) {
      throw new TaskCreationException(e.getMessage());
    }
  }

  public void editTask(
      String taskName, double hours, String resourceType, String startDate, String endDate, int id)
      throws TaskCreationException, SystemException {

    Task task =
        new Task.TaskBuilder()
            .name(taskName)
            .hours(hours)
            .resourceType(findResourceTypeByName(resourceType))
            .startDate(convertStringToDate(startDate))
            .endDate(convertStringToDate(endDate))
            .id(id)
            .build();

    TASK_REPOSITORY.editTask(task);
  }
}
