package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.SubProjectRepository;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Subproject;

import java.time.LocalDate;

public class SubProjectService {

  private final SubProjectRepository SUBPROJECT_REPOSITORY;

  public SubProjectService(SubProjectRepository subProjectRepository) {
    this.SUBPROJECT_REPOSITORY = subProjectRepository;
  }

  /**
   * Creates a subproject.
   *
   * @author Jackie
   */
  public Subproject createSubProject(
      String name, LocalDate startDate, LocalDate endDate, String projectColor, int parentProjectId)
      throws SystemException {
    return SUBPROJECT_REPOSITORY.createSubProject(
        name, startDate, endDate, projectColor, parentProjectId);
  }

  /**
   * Method for creating a subproject with the new state of the project.
   *
   * @author Mathias
   */
  public void editProject(
      int projectId, String name, LocalDate startDate, LocalDate endDate, String projectColor)
      throws SystemException {

    Subproject subproject =
        new Subproject.SubProjectBuilder()
            .subProjectId(projectId)
            .name(name)
            .startDate(startDate)
            .endDate(endDate)
            .colorCode(projectColor)
            .build();

    SUBPROJECT_REPOSITORY.editProject(subproject);
  }

  /**
   * Method for forwarding delete request to the repository
   *
   * @author Mathias
   */
  public void deleteProject(int projectId) throws SystemException {
    SUBPROJECT_REPOSITORY.deleteProject(projectId);
  }
}
