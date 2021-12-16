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
   * object. This task object is then send to the repository which returns a new task.
   *
   * <p>The Task created here is incomplete. @Author Andreas
   *
   * @param taskName
   * @param hours
   * @param resourceType
   * @param startDate
   * @param endDate
   * @param projectId
   * @return
   * @throws TaskCreationException
   * @throws SystemException
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
   * Utility method for converting a date given as a string to LocalDate @Author Andreas
   *
   * @param stringDate
   * @return
   */
  private LocalDate convertStringToDate(String stringDate) {
    return LocalDate.parse(stringDate);
  }

  /**
   * Method for retrieving all tasks bases on projectID.
   *
   * @param projectId
   * @return
   * @throws SystemException
   */
  public List<Task> getTasks(int projectId) throws SystemException {
    return TASK_REPOSITORY.getTasks(projectId);
  }

  /**
   * Method for retrieving resourcetypes. This is used for dynamicly making drop downs in the
   * html @Author Andreas
   *
   * @return
   * @throws SystemException
   */
  public List<ResourceType> getResourceTypes() throws SystemException {
    return RESOURCE_TYPE_REPOSITORY.getResourceTypes();
  }

  /**
   * Method for getting a resourcetype by its name.
   *
   * <p>When recieving a webrequest the reourcename is a string. This validates it and converts to a
   * RessourceType object. @Auther Andreas
   *
   * @param resourceTypeName
   * @return a ResourceType
   * @throws TaskCreationException Thrown when the ressourcetype was not found.
   */
  private ResourceType getResourceTypeByName(String resourceTypeName) throws TaskCreationException {
    try {
      return RESOURCE_TYPE_REPOSITORY.getByResourceTypeName(resourceTypeName);
    } catch (ResourceTypeNotFoundException e) {
      throw new TaskCreationException(e.getMessage());
    }
  }

  /**
   * Method for updating a Task with new information.
   *
   * <p>Due to builder pattern we have to create a new object in and store that to the
   * database. @Author Mathias
   *
   * @param taskName
   * @param hours
   * @param resourceType
   * @param startDate
   * @param endDate
   * @param id
   * @throws TaskCreationException
   * @throws SystemException
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
   * Method for deleting a task. @Author Mathias
   *
   * @param taskId
   * @throws SystemException
   */
  public void deleteTask(int taskId) throws SystemException {
    TASK_REPOSITORY.deleteTask(taskId);
  }
}
