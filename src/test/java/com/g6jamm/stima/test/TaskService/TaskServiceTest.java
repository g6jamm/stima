package com.g6jamm.stima.test.TaskService;

import com.g6jamm.stima.data.repository.mock.TaskRepositoryStub;
import com.g6jamm.stima.domain.model.Task;
import com.g6jamm.stima.domain.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskServiceTest {

    @Test
    public void createValidUserTest(){
        TaskService taskService = new TaskService(new TaskRepositoryStub());
        Task task = taskService.createtask("Test",2.5, "TestResource","1990-01-01","1990-01-01");

        Assertions.assertEquals(1, task.getID());
        
    }
}
