package com.g6jamm.stima.data.repository.mock;

import com.g6jamm.stima.data.repository.ProjectRepository;
import com.g6jamm.stima.domain.model.Project;

import java.time.LocalDate;

public class ProjectRepositoryStub implements ProjectRepository {

  @Override
  public Project createProject() {

    return new Project.ProjectBuilder()
        .projectName("Demo")
        .startDate(LocalDate.of(2021, 1, 1))
        .endDate(LocalDate.of(2021, 1, 2))
        .totalPrice(20000)
        .totalHours(500.0)
        // .tasks()
        // .subProjects()
        .build();
  }

  @Override
  public Project getProject(int projectId) {
    return null;
  }

  @Override
  public Project deleteProject(int projectId) {
    return null;
  }

  @Override
  public Project editProject(int projectId) {
    return null;
  }

  @Override
  public Project getTotalHoursOfProject(int projectId) {
    return null;
  }
}
