package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.stub.ProjectColorStub;
import com.g6jamm.stima.data.repository.stub.ProjectRepositoryStub;
import com.g6jamm.stima.domain.model.Project;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ProjectService {

  private final ProjectRepositoryStub PROJECT_REPOSITORY_STUB;

  public ProjectService(ProjectRepositoryStub projectRepositoryStub) {
    this.PROJECT_REPOSITORY_STUB = projectRepositoryStub;
  }

  public List<Project> getProjects() {
    return PROJECT_REPOSITORY_STUB.getProjects();
  }

  public Project getProjectById(int id) {
    for (Project project : getProjects()) {
      if (id == project.getId()) {
        return project;
      }
    }

    return null;
  }

  public Project createProject(String name, LocalDate startDate, LocalDate endDate, String projectColor) {
    return PROJECT_REPOSITORY_STUB.createProject(name, startDate, endDate, projectColor);
  }
}
