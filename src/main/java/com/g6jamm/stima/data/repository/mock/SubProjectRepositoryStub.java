package com.g6jamm.stima.data.repository.mock;

import com.g6jamm.stima.data.repository.SubProjectRepository;
import com.g6jamm.stima.domain.model.SubProject;
import com.g6jamm.stima.domain.model.Task;

import java.time.LocalDate;
import java.util.List;

public class SubProjectRepositoryStub implements SubProjectRepository {

  @Override
  public SubProject getSubProject(int id) {
    return null;
  }

  @Override
  public SubProject createSubProject(String name, LocalDate startDate, LocalDate endDate) {

    return new SubProject.SubProjectBuilder()
        .name(name)
        .hours(0)
        .price(0)
        .tasks(null)
        .startDate(startDate)
        .endDate(endDate)
        .build();
  }

  @Override
  public SubProject deleteSubProject(SubProject subProject) {
    return subProject;
  }

  @Override
  public double getTotalHours(SubProject subProject) {
    int result = 0;
    List<Task> tasks = subProject.getTasks();

    for (Task t : tasks) {
      result += t.getHours();
    }

    return result;
  }

  @Override
  public int getTotalPrice(SubProject subProject) {
    int result = 0;
    List<Task> tasks = subProject.getTasks();

    for (Task t : tasks) {
      result += t.getPrice();
    }

    return result;
  }
}
