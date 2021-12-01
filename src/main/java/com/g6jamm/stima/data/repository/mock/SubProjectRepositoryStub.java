package com.g6jamm.stima.data.repository.mock;

import com.g6jamm.stima.data.repository.SubProjectRepository;
import com.g6jamm.stima.domain.model.SubProject;
import com.g6jamm.stima.domain.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SubProjectRepositoryStub implements SubProjectRepository {

  private static List<SubProject> subProjects = new ArrayList<>();

  public SubProjectRepositoryStub() {
    if (subProjects.isEmpty()) {
      subProjects.add(
          new SubProject.SubProjectBuilder()
              .name("example")
              .hours(100)
              .price(80000)
              .startDate(LocalDate.of(2020, 1, 1))
              .endDate(LocalDate.of(2021, 1, 1))
              .build());

      subProjects.add(
          new SubProject.SubProjectBuilder()
              .name("example2")
              .hours(100)
              .price(80000)
              .startDate(LocalDate.of(2020, 1, 1))
              .endDate(LocalDate.of(2021, 1, 1))
              .build());
    }
  }

  @Override
  public List<SubProject> getSubProjects() {
    return subProjects;
  }

  @Override
  public SubProject createSubProject(String name, LocalDate startDate, LocalDate endDate) {

    SubProject subProject = new SubProject.SubProjectBuilder()
        .name(name)
        // .hours(0)
        // .price(0)
        // .tasks(null)
        .startDate(startDate)
        .endDate(endDate)
        .build();

    subProjects.add(subProject);

    return subProject;
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
