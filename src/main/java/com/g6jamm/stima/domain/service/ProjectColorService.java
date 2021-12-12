package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.ProjectColorRepository;

import java.util.Map;

public class ProjectColorService {
  private final ProjectColorRepository PROJECT_COLOR_REPOSITORY_STUB;

  public ProjectColorService(ProjectColorRepository projectColorRepositoryStub) {
    this.PROJECT_COLOR_REPOSITORY_STUB = projectColorRepositoryStub;
  }

  public Map<String, String> getProjectColors() {
    return PROJECT_COLOR_REPOSITORY_STUB.getProjectColors(); // TODO this will need a refactor
  }
}
