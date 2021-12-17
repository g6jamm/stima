package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.ProjectColorRepository;
import com.g6jamm.stima.domain.exception.SystemException;

import java.util.Map;

public class ProjectColorService {
  private final ProjectColorRepository PROJECT_COLOR_REPOSITORY;

  /** @auther Mathias */
  public ProjectColorService(ProjectColorRepository projectColorRepository) {
    this.PROJECT_COLOR_REPOSITORY = projectColorRepository;
  }

  /**
   * Returns a map of available colors from the repository.
   *
   * @auther Mathias
   */
  public Map<String, String> getProjectColors() throws SystemException {
    return PROJECT_COLOR_REPOSITORY.getProjectColors();
  }
}
