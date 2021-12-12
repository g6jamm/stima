package com.g6jamm.stima.data.repository.stub;

import com.g6jamm.stima.data.repository.ProjectRepository;
import com.g6jamm.stima.data.repository.SubProjectRepository;
import com.g6jamm.stima.data.repository.TaskRepository;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.ProjectComposite;
import com.g6jamm.stima.domain.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryImpl implements ProjectRepository {

  private static final List<ProjectComposite> projects = new ArrayList<>();
  private final TaskRepository TASK_REPOSITORY = new TaskRepositoryImpl();
  private final SubProjectRepository SUBPROJECT_REPOSITORY = new SubProjectRepositoryImpl();

  public ProjectRepositoryImpl() throws SystemException {
    createTestData();
  }

  public void createTestData() throws SystemException {
    if (projects.isEmpty()) {
      projects.add(
          new ProjectComposite.ProjectBuilder()
              .projectId(projects.size() + 1)
              .projectName("Projekt pink")
              .startDate(LocalDate.of(2021, 1, 1))
              .endDate(LocalDate.of(2021, 1, 2))
              .tasks(TASK_REPOSITORY.getTasks(1))
              .subProjects(SUBPROJECT_REPOSITORY.getSubProjects(1))
              .colorCode("pink")
              .build());

      projects.add(
          new ProjectComposite.ProjectBuilder()
              .projectId(projects.size() + 1)
              .projectName("Projekt lilla")
              .startDate(LocalDate.of(2021, 1, 1))
              .endDate(LocalDate.of(2021, 1, 2))
              .tasks(TASK_REPOSITORY.getTasks(1))
              .subProjects(SUBPROJECT_REPOSITORY.getSubProjects(1))
              .colorCode("purple")
              .build());

      projects.add(
          new ProjectComposite.ProjectBuilder()
              .projectId(projects.size() + 1)
              .projectName("Projekt grøn")
              .startDate(LocalDate.of(2021, 1, 1))
              .endDate(LocalDate.of(2021, 1, 2))
              .tasks(TASK_REPOSITORY.getTasks(1))
              .subProjects(SUBPROJECT_REPOSITORY.getSubProjects(1))
              .colorCode("green")
              .build());

      projects.add(
          new ProjectComposite.ProjectBuilder()
              .projectId(projects.size() + 1)
              .projectName("Projekt brun")
              .startDate(LocalDate.of(2021, 1, 1))
              .endDate(LocalDate.of(2021, 1, 2))
              .tasks(TASK_REPOSITORY.getTasks(1))
              .subProjects(SUBPROJECT_REPOSITORY.getSubProjects(1))
              .colorCode("brown")
              .build());
    }
  }

  @Override
  public ProjectComposite createProject(ProjectComposite project, User user)
      throws SystemException {

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
  public ProjectComposite getProject(int projectId) throws SystemException {
    return null;
  }

  @Override
  public List<ProjectComposite> getProjects(User user) throws SystemException {
    return projects;
  }

  @Override
  public void deleteProject(int projectId) throws SystemException {}

  @Override
  public void editProject(ProjectComposite project) throws SystemException {}
}
