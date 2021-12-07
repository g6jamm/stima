package com.g6jamm.stima.data.repository.stub;

import com.g6jamm.stima.data.repository.ProjectRepository;
import com.g6jamm.stima.domain.model.Project;
import com.g6jamm.stima.domain.model.SubProject;
import com.g6jamm.stima.domain.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryStub implements ProjectRepository {

  private static final List<Project> projects = new ArrayList<>();

  public ProjectRepositoryStub() {
    if (projects.isEmpty()) {

      TaskRepositoryStub taskRepositoryStub = new TaskRepositoryStub();
      SubProjectRepositoryStub subProjectRepositoryStub = new SubProjectRepositoryStub();

      projects.add(
          new Project.ProjectBuilder()
              .projectId(projects.size() + 1)
              .projectName("Projekt pink")
              .startDate(LocalDate.of(2021, 1, 1))
              .endDate(LocalDate.of(2021, 1, 2))
              .tasks(taskRepositoryStub.getTasks(1))
              .subProjects(subProjectRepositoryStub.getSubProjects(projects.size() + 1))
              .colorCode("pink")
              .build());

      projects.add(
          new Project.ProjectBuilder()
              .projectId(projects.size() + 1)
              .projectName("Projekt lilla")
              .startDate(LocalDate.of(2021, 1, 1))
              .endDate(LocalDate.of(2021, 1, 2))
              .tasks(new ArrayList<Task>())
              .subProjects(new ArrayList<SubProject>())
              .colorCode("purple")
              .build());

      projects.add(
          new Project.ProjectBuilder()
              .projectId(projects.size() + 1)
              .projectName("Projekt grøn")
              .startDate(LocalDate.of(2021, 1, 1))
              .endDate(LocalDate.of(2021, 1, 2))
              .tasks(new ArrayList<Task>())
              .subProjects(new ArrayList<SubProject>())
              .colorCode("green")
              .build());

      projects.add(
          new Project.ProjectBuilder()
              .projectId(projects.size() + 1)
              .projectName("Projekt brun")
              .startDate(LocalDate.of(2021, 1, 1))
              .endDate(LocalDate.of(2021, 1, 2))
              .tasks(new ArrayList<Task>())
              .subProjects(new ArrayList<SubProject>())
              .colorCode("brown")
              .build());
    }
  }

  @Override
  public Project createProject(Project project) {

    Project newProject =
        new Project.ProjectBuilder()
            .projectId(projects.size() + 1)
            .projectName(project.getName())
            .startDate(project.getStartDate())
            .endDate(project.getEndDate())
            .colorCode(project.getColorCode())
            .tasks(new ArrayList<Task>())
            .subProjects(new ArrayList<SubProject>())
            .build();

    projects.add(newProject);
    return project;
  }

  @Override
  public Project getProject(int projectId) {
    return null;
  }

  public List<Project> getProjects() {
    return projects;
  }

  @Override
  public void deleteProject(int projectId) {}

  @Override
  public void editProject(Project project) {}
}
