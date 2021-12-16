package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.stub.ResourceTypeRepositoryImpl;
import com.g6jamm.stima.data.repository.stub.TaskRepositoryImpl;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.exception.TaskCreationException;
import com.g6jamm.stima.domain.model.Task;
import com.g6jamm.stima.domain.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/** @author Andreas */
public class TaskServiceTest {

  @Test
  public void testCreateValidTask() throws TaskCreationException, SystemException {
    TaskService taskService =
        new TaskService(new TaskRepositoryImpl(), new ResourceTypeRepositoryImpl());
    Task task =
        taskService.createTask("Test", 2.5, "Project Manager", "1990-01-01", "1990-01-01", 1);

    Assertions.assertEquals(3, task.getId());
  }

  @Test
  public void testPriceCalculation() throws SystemException {
    TaskService taskService =
        new TaskService(new TaskRepositoryImpl(), new ResourceTypeRepositoryImpl());
    Task task = taskService.getTasks(1).get(0);
    Assertions.assertEquals(5000, task.getPrice());
  }

  @Test
  public void testPriceCalculationOnCreateTask() throws TaskCreationException, SystemException {
    TaskService taskService =
        new TaskService(new TaskRepositoryImpl(), new ResourceTypeRepositoryImpl());
    Task task =
        taskService.createTask("Test", 2.5, "Senior Developer", "1990-01-01", "1990-01-01", 1);

    Assertions.assertEquals((int) 2.5 * 1250, task.getPrice());
  }

  @Test
  public void testExceptionOnNonExistingResourceType() throws SystemException {
    TaskService taskService =
        new TaskService(new TaskRepositoryImpl(), new ResourceTypeRepositoryImpl());
    String resourceName = "This does not exist";
    Assertions.assertThrows(
        TaskCreationException.class,
        () -> taskService.createTask("test", 2.1, resourceName, "1111-11-11", "1111-11-11", 1));
  }
}
