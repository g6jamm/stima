package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.ProjectRepository;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Headproject;
import com.g6jamm.stima.domain.model.User;

import java.time.LocalDate;
import java.util.List;

public class ProjectService {

  private final ProjectRepository PROJECT_REPOSITORY;

  public ProjectService(ProjectRepository projectRepository) {
    this.PROJECT_REPOSITORY = projectRepository;
  }

  /** @auther Mathias */
  public List<Headproject> getProjects(User user) throws SystemException {
    return PROJECT_REPOSITORY.getProjects(user);
  }

  /** @auther Mathias */
  public Headproject getProjectById(User user, int projectID) throws SystemException {
    for (Headproject project : getProjects(user)) {
      if (projectID == project.getId()) {
        return project;
      }
    }

    return null;
  }

  /** @auther Mathias */
  public Headproject createProject(
      String name, LocalDate startDate, LocalDate endDate, String projectColor, User user)
      throws SystemException {

    Headproject project =
        new Headproject.ProjectBuilder()
            .projectName(name)
            .startDate(startDate)
            .endDate(endDate)
            .colorCode(projectColor)
            .build();

    return PROJECT_REPOSITORY.createProject(project, user);
  }

  /** @auther Mathias */
  public void editProject(
      int projectId, String name, LocalDate startDate, LocalDate endDate, String projectColor)
      throws SystemException {

    Headproject project =
        new Headproject.ProjectBuilder()
            .projectId(projectId)
            .projectName(name)
            .startDate(startDate)
            .endDate(endDate)
            .colorCode(projectColor)
            .build();

    PROJECT_REPOSITORY.editProject(project);
  }

  /** @auther Mathias */
  public void deleteProject(int projectId) throws SystemException {
    PROJECT_REPOSITORY.deleteProject(projectId);
  }
}
