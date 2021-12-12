package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.ProjectRepository;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.ProjectComposite;
import com.g6jamm.stima.domain.model.User;

import java.time.LocalDate;
import java.util.List;

public class ProjectService {

  private final ProjectRepository PROJECT_REPOSITORY;

  public ProjectService(ProjectRepository projectRepository) {
    this.PROJECT_REPOSITORY = projectRepository;
  }

  public List<ProjectComposite> getProjects(User user) throws SystemException {
    return PROJECT_REPOSITORY.getProjects(user);
  }

  public ProjectComposite getProjectById(User user, int projectID) throws SystemException {
    for (ProjectComposite project : getProjects(user)) {
      if (projectID == project.getId()) {
        return project;
      }
    }

    return null;
  }

  public ProjectComposite createProject(
      String name, LocalDate startDate, LocalDate endDate, String projectColor, User user)
      throws SystemException {

    ProjectComposite project =
        new ProjectComposite.ProjectBuilder()
            .projectName(name)
            .startDate(startDate)
            .endDate(endDate)
            .colorCode(projectColor)
            .build();

    return PROJECT_REPOSITORY.createProject(project, user);
  }

  public void editProject(
      int projectId, String name, LocalDate startDate, LocalDate endDate, String projectColor)
      throws SystemException {

    ProjectComposite project =
        new ProjectComposite.ProjectBuilder()
            .projectId(projectId)
            .projectName(name)
            .startDate(startDate)
            .endDate(endDate)
            .colorCode(projectColor)
            .build();

    PROJECT_REPOSITORY.editProject(project);
  }

  public void deleteProject(int projectId) throws SystemException {
    PROJECT_REPOSITORY.deleteProject(projectId);
  }
}
