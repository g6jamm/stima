package com.g6jamm.stima.test.TaskService;

import com.g6jamm.stima.data.repository.stub.TaskRepositoryStub;
import com.g6jamm.stima.domain.model.Task;
import com.g6jamm.stima.domain.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskServiceTest {

  @Test
  public void createValidTaskTest() {
    TaskService taskService = new TaskService(new TaskRepositoryStub());
    Task task = taskService.createtask("Test", 2.5, "TestResource", "1990-01-01", "1990-01-01");

    Assertions.assertEquals(3, task.getId());
  }
}
