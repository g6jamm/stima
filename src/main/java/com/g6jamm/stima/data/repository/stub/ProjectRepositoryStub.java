package com.g6jamm.stima.data.repository.stub;

import com.g6jamm.stima.data.repository.ProjectRepository;
import com.g6jamm.stima.domain.model.Project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryStub implements ProjectRepository {

  private static final List<Project> projects = new ArrayList<>();

  public ProjectRepositoryStub() {
    if (projects.isEmpty()) {

      projects.add(
          new Project.ProjectBuilder()
              .projectId(projects.size() + 1)
              .projectName("Projekt pink")
              .startDate(LocalDate.of(2021, 1, 1))
              .endDate(LocalDate.of(2021, 1, 2))
              .totalPrice(4000000)
              .totalHours(300)
              // .tasks()
              // .subProjects()
              .colorCode("pink")
              .build());

      projects.add(
          new Project.ProjectBuilder()
              .projectId(projects.size() + 1)
              .projectName("Projekt lilla")
              .startDate(LocalDate.of(2021, 1, 1))
              .endDate(LocalDate.of(2021, 1, 2))
              .totalPrice(7000000)
              .totalHours(800)
              // .tasks()
              // .subProjects()
              .colorCode("purple")
              .build());

      projects.add(
          new Project.ProjectBuilder()
              .projectId(projects.size() + 1)
              .projectName("Projekt grøn")
              .startDate(LocalDate.of(2021, 1, 1))
              .endDate(LocalDate.of(2021, 1, 2))
              .totalPrice(200000)
              .totalHours(60)
              // .tasks()
              // .subProjects()
              .colorCode("green")
              .build());

      projects.add(
          new Project.ProjectBuilder()
              .projectId(projects.size() + 1)
              .projectName("Projekt brun")
              .startDate(LocalDate.of(2021, 1, 1))
              .endDate(LocalDate.of(2021, 1, 2))
              .totalPrice(12000000)
              .totalHours(120)
              // .tasks()
              // .subProjects()
              .colorCode("brown")
              .build());
    }
  }

  @Override
  public Project createProject(
      String name, LocalDate startDate, LocalDate endDate, String projectColor) {

    Project newProject =
        new Project.ProjectBuilder()
            .projectId(projects.size() + 1)
            .projectName(name)
            .startDate(startDate)
            .endDate(endDate)
            .totalPrice(20000)
            .totalHours(500.0)
            .colorCode(projectColor)
            // .tasks()
            // .subProjects()
            .build();

    projects.add(newProject);
    return newProject;
  }

  @Override
  public Project getProject(int projectId) {
    return null;
  }

  public List<Project> getProjects() {
    return projects;
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
