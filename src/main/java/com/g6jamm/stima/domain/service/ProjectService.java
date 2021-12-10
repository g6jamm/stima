package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.ProjectRepository;
import com.g6jamm.stima.domain.model.ProjectComposite;

import java.time.LocalDate;
import java.util.List;

public class ProjectService {

  private final ProjectRepository PROJECT_REPOSITORY;

  public ProjectService(ProjectRepository projectRepository) {
    this.PROJECT_REPOSITORY = projectRepository;
  }

  public List<ProjectComposite> getProjects() {
    return PROJECT_REPOSITORY.getProjects();
  }

  public ProjectComposite getProjectById(int id) {
    for (ProjectComposite project : getProjects()) {
      if (id == project.getId()) {
        return project;
      }
    }

    return null;
  }

  public ProjectComposite createProject(
      String name, LocalDate startDate, LocalDate endDate, String projectColor) {

    ProjectComposite project =
        new ProjectComposite.ProjectBuilder()
            .projectName(name)
            .startDate(startDate)
            .endDate(endDate)
            .colorCode(projectColor)
            .build();

    return PROJECT_REPOSITORY.createProject(project);
  }

  public void editProject(
      int projectId, String name, LocalDate startDate, LocalDate endDate, String projectColor) {

    ProjectComposite project =
        new ProjectComposite.ProjectBuilder()
            .projectId(projectId)
            .projectName(name)
            .startDate(startDate)
            .endDate(endDate)
            .colorCode("1") // TODO: @Jacky
            .build();

    PROJECT_REPOSITORY.editProject(project);
  }

  public void deleteProject(int projectId) {
    PROJECT_REPOSITORY.deleteProject(projectId);
  }
}
