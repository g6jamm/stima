package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Headproject;
import com.g6jamm.stima.domain.model.Project;
import com.g6jamm.stima.domain.model.Subproject;

import java.time.LocalDate;
import java.util.List;

/** @author Jackie */
public interface SubProjectRepository {

  List<Project> getSubProjects(int projectId) throws SystemException;

  Subproject createSubProject(
      String name,
      LocalDate startDate,
      LocalDate endDate,
      String projectColorParam,
      int parentProjectId)
      throws SystemException;

  void editProject(Headproject project) throws SystemException;

  void deleteProject(int projectId) throws SystemException;
}
