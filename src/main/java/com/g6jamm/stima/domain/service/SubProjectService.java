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
   * Generate a subproject, with start parameter. hours = 0, price = 0
   *
   * @param name
   * @param startDate
   * @param endDate
   * @return
   * @author Jackie
   */
  public Subproject createSubProject(
      String name, LocalDate startDate, LocalDate endDate, String projectColor, int parentProjectId)
      throws SystemException {
    return SUB_PROJECT_REPOSITORY.createSubProject(
        name, startDate, endDate, projectColor, parentProjectId); // TODO: send object med
  }

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

  public void deleteProject(int projectId) throws SystemException {
    SUB_PROJECT_REPOSITORY.deleteProject(projectId);
  }
}
