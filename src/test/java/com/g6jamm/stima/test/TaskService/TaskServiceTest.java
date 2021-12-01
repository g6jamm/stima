package com.g6jamm.stima.test.TaskService;

import com.g6jamm.stima.data.repository.stub.ResourceTypeRepositoryStub;
import com.g6jamm.stima.data.repository.stub.TaskRepositoryStub;
import com.g6jamm.stima.domain.exception.TaskCreationException;
import com.g6jamm.stima.domain.model.Task;
import com.g6jamm.stima.domain.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskServiceTest {

  @Test
  public void createValidTaskTest() throws TaskCreationException{
    TaskService taskService = new TaskService(new TaskRepositoryStub(), new ResourceTypeRepositoryStub());
    Task task = taskService.createtask("Test", 2.5, "Project Manager", "1990-01-01", "1990-01-01");

    Assertions.assertEquals(3, task.getId());
  }


  @Test
  public void testPriceCalculation(){
    TaskService taskService = new TaskService(new TaskRepositoryStub(), new ResourceTypeRepositoryStub());
    Task task = taskService.getTasks().get(0);
    //expected is 5000 - Task 0 in stub is hours = 5 and pricePrHour = 1000
    Assertions.assertEquals(5000, task.getPrice());
  }



  @Test
  public void testPriceCalculationOnCreateTask() throws TaskCreationException {
    TaskService taskService = new TaskService(new TaskRepositoryStub(), new ResourceTypeRepositoryStub());
    Task task = taskService.createtask("Test", 2.5, "Senior Developer", "1990-01-01", "1990-01-01");

    Assertions.assertEquals((int) 2.5 * 1250, task.getPrice());
  }

  @Test
  public void testExceptionOnNonExistingResourceType(){
    TaskService taskService = new TaskService(new TaskRepositoryStub(), new ResourceTypeRepositoryStub());
    String resourceName = "This does not exist";
    Assertions.assertThrows(TaskCreationException.class,() -> taskService.createtask("test",2.1,resourceName, "1111-11-11","1111-11-11"));
  }
}
