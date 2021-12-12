package com.g6jamm.stima.data.repository.stub;

import com.g6jamm.stima.data.repository.ProjectRepository;
import com.g6jamm.stima.domain.model.ProjectComposite;
import com.g6jamm.stima.domain.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryImpl implements ProjectRepository {

  private static final List<ProjectComposite> projects = new ArrayList<>();

  public ProjectRepositoryImpl() {
    if (projects.isEmpty()) {

      TaskRepositoryImpl taskRepositoryImpl = new TaskRepositoryImpl();
      SubProjectRepositoryImpl subProjectRepositoryStub = new SubProjectRepositoryImpl();

      projects.add(
          new ProjectComposite.ProjectBuilder()
              .projectId(projects.size() + 1)
              .projectName("Projekt pink")
              .startDate(LocalDate.of(2021, 1, 1))
              .endDate(LocalDate.of(2021, 1, 2))
              .tasks(taskRepositoryImpl.getTasks(1))
              .subProjects(subProjectRepositoryStub.getSubProjects(1))
              .colorCode("pink")
              .build());

      projects.add(
          new ProjectComposite.ProjectBuilder()
              .projectId(projects.size() + 1)
              .projectName("Projekt lilla")
              .startDate(LocalDate.of(2021, 1, 1))
              .endDate(LocalDate.of(2021, 1, 2))
              .tasks(new ArrayList<>())
              .subProjects(new ArrayList<>())
              .colorCode("purple")
              .build());

      projects.add(
          new ProjectComposite.ProjectBuilder()
              .projectId(projects.size() + 1)
              .projectName("Projekt grøn")
              .startDate(LocalDate.of(2021, 1, 1))
              .endDate(LocalDate.of(2021, 1, 2))
              .tasks(new ArrayList<>())
              .subProjects(new ArrayList<>())
              .colorCode("green")
              .build());

      projects.add(
          new ProjectComposite.ProjectBuilder()
              .projectId(projects.size() + 1)
              .projectName("Projekt brun")
              .startDate(LocalDate.of(2021, 1, 1))
              .endDate(LocalDate.of(2021, 1, 2))
              .tasks(new ArrayList<>())
              .subProjects(new ArrayList<>())
              .colorCode("brown")
              .build());
    }
  }

  @Override
  public ProjectComposite createProject(ProjectComposite project, User user) {

    ProjectComposite newProject =
        new ProjectComposite.ProjectBuilder()
            .projectId(projects.size() + 1)
            .projectName(project.getName())
            .startDate(project.getStartDate())
            .endDate(project.getEndDate())
            .colorCode(project.getColorCode())
            .tasks(new ArrayList<>())
            .subProjects(new ArrayList<>())
            .build();

    projects.add(newProject);
    return project;
  }

  @Override
  public ProjectComposite getProject(int projectId) {
    return null;
  }

  @Override
  public List<ProjectComposite> getProjects(User user) {
    return projects;
  }

  @Override
  public void deleteProject(int projectId) {}

  @Override
  public void editProject(ProjectComposite project) {}
}
