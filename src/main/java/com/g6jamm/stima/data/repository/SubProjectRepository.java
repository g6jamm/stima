package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Headproject;
import com.g6jamm.stima.domain.model.Project;
import com.g6jamm.stima.domain.model.Subproject;

import java.time.LocalDate;
import java.util.List;

public interface SubProjectRepository {

  /**
   * Implementation for a list with sub projects
   *
   * @param projectId
   * @return
   * @throws SystemException
   * @author Jackie
   */
  List<Project> getSubProjects(int projectId) throws SystemException;

  /**
   * Implementation for creating a sub project
   *
   * @param name
   * @param startDate
   * @param endDate
   * @param projectColorParam
   * @param parentProjectId
   * @return
   * @throws SystemException
   * @author Jackie
   */
  Subproject createSubProject(
      String name,
      LocalDate startDate,
      LocalDate endDate,
      String projectColorParam,
      int parentProjectId)
      throws SystemException;

  /**
   * Implementation for editing a sub project
   *
   * @param project
   * @throws SystemException
   * @author Jackie
   */
  void editProject(Headproject project) throws SystemException;

  /**
   * Implementation for deliging a sub project
   *
   * @param projectId
   * @throws SystemException
   * @author Jackie
   */
  void deleteProject(int projectId) throws SystemException;
}
