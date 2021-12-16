package com.g6jamm.stima.data.repository.stub;

import com.g6jamm.stima.data.repository.ProjectRepository;
import com.g6jamm.stima.data.repository.SubProjectRepository;
import com.g6jamm.stima.data.repository.TaskRepository;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Headproject;
import com.g6jamm.stima.domain.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryImpl implements ProjectRepository {

  private static final List<Headproject> projects = new ArrayList<>();
  private final TaskRepository TASK_REPOSITORY = new TaskRepositoryImpl();
  private final SubProjectRepository SUBPROJECT_REPOSITORY = new SubProjectRepositoryImpl();

  public ProjectRepositoryImpl() throws SystemException {
    createTestData();
  }

  /** @auther Mathias, Andreas */
  public void createTestData() throws SystemException {
    if (projects.isEmpty()) {
      projects.add(
          new Headproject.ProjectBuilder()
              .projectId(projects.size() + 1)
              .projectName("Projekt pink")
              .startDate(LocalDate.of(2021, 1, 1))
              .endDate(LocalDate.of(2021, 1, 2))
              .tasks(TASK_REPOSITORY.getTasks(1))
              .subProjects(SUBPROJECT_REPOSITORY.getSubProjects(1))
              .colorCode("pink")
              .build());

      projects.add(
          new Headproject.ProjectBuilder()
              .projectId(projects.size() + 1)
              .projectName("Projekt lilla")
              .startDate(LocalDate.of(2021, 1, 1))
              .endDate(LocalDate.of(2021, 1, 2))
              .tasks(TASK_REPOSITORY.getTasks(1))
              .subProjects(SUBPROJECT_REPOSITORY.getSubProjects(1))
              .colorCode("purple")
              .build());

      projects.add(
          new Headproject.ProjectBuilder()
              .projectId(projects.size() + 1)
              .projectName("Projekt grøn")
              .startDate(LocalDate.of(2021, 1, 1))
              .endDate(LocalDate.of(2021, 1, 2))
              .tasks(TASK_REPOSITORY.getTasks(1))
              .subProjects(SUBPROJECT_REPOSITORY.getSubProjects(1))
              .colorCode("green")
              .build());

      projects.add(
          new Headproject.ProjectBuilder()
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

  /** @auther Mathias */
  @Override
  public Headproject createProject(Headproject project, User user) throws SystemException {

    Headproject newProject =
        new Headproject.ProjectBuilder()
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

  /** @auther Mathias */
  @Override
  public List<Headproject> getProjects(User user) throws SystemException {
    return projects;
  }

  /** This part is not implemented yet. */
  @Override
  public void deleteProject(int projectId) throws SystemException {}

  /** This part is not implemented yet. */
  @Override
  public void editProject(Headproject project) throws SystemException {}
}
