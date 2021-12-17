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

  /**
   * Service method for creating a task, in order for us to create a task we need to build a task
   * object. This task object is then send to the repository which returns a new task. The Task
   * created here is incomplete.
   *
   * @author Andreas
   */
  public Task createTask(
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
            .resourceType(getResourceTypeByName(resourceType))
            .startDate(convertStringToDate(startDate))
            .endDate(convertStringToDate(endDate))
            .build();
    return TASK_REPOSITORY.createTask(newTask, projectId);
  }

  /**
   * Utility method for converting a date given as a string to LocalDate
   *
   * @author Andreas
   */
  private LocalDate convertStringToDate(String stringDate) {
    return LocalDate.parse(stringDate);
  }

  /**
   * Method for retrieving all tasks bases on projectID.
   *
   * @auther Andreas
   */
  public List<Task> getTasks(int projectId) throws SystemException {
    return TASK_REPOSITORY.getTasks(projectId);
  }

  /**
   * Method for retrieving resource types. This is used for dynamically making drop-downs in the
   * html @author Andreas
   *
   * @auther Mathias
   */
  public List<ResourceType> getResourceTypes() throws SystemException {
    return RESOURCE_TYPE_REPOSITORY.getResourceTypes();
  }

  /**
   * Method for getting a resource type by its name. When receiving a web-request the reoccurrence
   * is a string. This validates it and converts to a RessourceType object.
   *
   * @auther Andreas
   */
  private ResourceType getResourceTypeByName(String resourceTypeName) throws TaskCreationException {
    try {
      return RESOURCE_TYPE_REPOSITORY.getByResourceTypeName(resourceTypeName);
    } catch (ResourceTypeNotFoundException e) {
      throw new TaskCreationException(e.getMessage());
    }
  }

  /**
   * Method for updating a Task with new information. Due to builder pattern we have to create a new
   * object in and store that to the database.
   *
   * @author Mathias
   */
  public void editTask(
      String taskName, double hours, String resourceType, String startDate, String endDate, int id)
      throws TaskCreationException, SystemException {

    Task task =
        new Task.TaskBuilder()
            .name(taskName)
            .hours(hours)
            .resourceType(getResourceTypeByName(resourceType))
            .startDate(convertStringToDate(startDate))
            .endDate(convertStringToDate(endDate))
            .id(id)
            .build();

    TASK_REPOSITORY.editTask(task);
  }

  /**
   * Method for deleting a task.
   *
   * @author Mathias
   */
  public void deleteTask(int taskId) throws SystemException {
    TASK_REPOSITORY.deleteTask(taskId);
  }
}
