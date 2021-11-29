package com.g6jamm.stima.data.repository.mock;

import com.g6jamm.stima.data.repository.TaskRepository;
import com.g6jamm.stima.domain.model.Role;
import com.g6jamm.stima.domain.model.Task;

import java.time.LocalDate;

public class TaskRepositoryStub implements TaskRepository {
    @Override
    public Task createTask(Task task) {
        task = new Task.TaskBuilder()
                .name("Test")
                .role(new Role())
                .price(1)
                .hours(1.0)
                .startDate(LocalDate.of(2021, 1, 1))
                .endDate(LocalDate.of(2021, 1, 1))
                .build();

        return task;
    }

    @Override
    public Task getTask(int task_id) {
        return null;
    }
}
