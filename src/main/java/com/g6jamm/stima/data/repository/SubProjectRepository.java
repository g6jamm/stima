package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Project;
import com.g6jamm.stima.domain.model.ProjectComposite;
import com.g6jamm.stima.domain.model.ProjectLeaf;
import com.g6jamm.stima.domain.model.Task;

import java.time.LocalDate;
import java.util.List;

/** @author Jackie */
public interface SubProjectRepository {

  List<Project> getSubProjects(int projectId) throws SystemException;

  Project getSubproject(int subProjectId) throws SystemException;

  ProjectLeaf createSubProject(
      String name,
      LocalDate startDate,
      LocalDate endDate,
      String projectColorParam,
      int parentProjectId)
      throws SystemException;

  ProjectLeaf deleteSubProject(int subProjectId);

  boolean addTaskToSubProject(int subProjectId, Task task);

  double getTotalHours(ProjectLeaf subProject);

  int getTotalPrice(ProjectLeaf subProject);

  void editProject(ProjectComposite project) throws SystemException;

  void deleteProject(int projectId) throws SystemException;
}
