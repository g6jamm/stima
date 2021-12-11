package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.model.Project;
import com.g6jamm.stima.domain.model.ProjectComposite;
import com.g6jamm.stima.domain.model.ProjectLeaf;
import com.g6jamm.stima.domain.model.Task;

import java.time.LocalDate;
import java.util.List;

/** @author Jackie */
public interface SubProjectRepository {

  List<Project> getSubProjects(int projectId);

  Project getSubproject(int subProjectId);

  ProjectLeaf createSubProject(
      String name,
      LocalDate startDate,
      LocalDate endDate,
      String projectColorParam,
      int parentProjectId);

  ProjectLeaf deleteSubProject(int subProjectId);

  boolean addTaskToSubProject(int subProjectId, Task task);

  double getTotalHours(ProjectLeaf subProject);

  int getTotalPrice(ProjectLeaf subProject);

  void editProject(ProjectComposite project);

  void deleteProject(int projectId);
}
