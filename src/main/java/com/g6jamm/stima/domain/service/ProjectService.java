package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.mock.ProjectRepositoryStub;
import com.g6jamm.stima.domain.model.HeadProject;

import java.util.List;

public class ProjectService {

  private final ProjectRepositoryStub PROJECT_REPOSITORY_STUB;

  public ProjectService(ProjectRepositoryStub projectRepositoryStub) {
    this.PROJECT_REPOSITORY_STUB = projectRepositoryStub;
  }

  public List<HeadProject> getProjects() {
    return PROJECT_REPOSITORY_STUB.getProjects();
  }
}
