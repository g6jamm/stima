package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.SubProjectRepository;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Headproject;
import com.g6jamm.stima.domain.model.Subproject;

import java.time.LocalDate;

public class SubProjectService {

  private final SubProjectRepository SUB_PROJECT_REPOSITORY;

  public SubProjectService(SubProjectRepository subProjectRepository) {
    this.SUB_PROJECT_REPOSITORY = subProjectRepository;
  }

  /**
   * Creates a subproject.
   *
   * @author Jackie
   */
  public Subproject createSubProject(
      String name, LocalDate startDate, LocalDate endDate, String projectColor, int parentProjectId)
      throws SystemException {
    return SUB_PROJECT_REPOSITORY.createSubProject(
        name, startDate, endDate, projectColor, parentProjectId); // TODO: @Jackie parse an object instead
  }

  /**
   * @auther Mathias
   */
  public void editProject(
      int projectId, String name, LocalDate startDate, LocalDate endDate, String projectColor)
      throws SystemException {

    Headproject subproject =
        new Headproject.ProjectBuilder()
            .projectId(projectId)
            .projectName(name)
            .startDate(startDate)
            .endDate(endDate)
            .colorCode(projectColor)
            .build();

    SUB_PROJECT_REPOSITORY.editProject(subproject);
  }

  /**
   * @auther Mathias
   */
  public void deleteProject(int projectId) throws SystemException {
    SUB_PROJECT_REPOSITORY.deleteProject(projectId);
  }
}
