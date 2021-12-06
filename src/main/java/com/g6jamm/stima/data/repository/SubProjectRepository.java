package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.model.SubProject;
import com.g6jamm.stima.domain.model.Task;

import java.time.LocalDate;
import java.util.List;

/** @author Jackie */
public interface SubProjectRepository {

  List<SubProject> getSubProjects(int projectId);

  SubProject getSubproject(int subProjectId);

  SubProject createSubProject(
      String name,
      LocalDate startDate,
      LocalDate endDate,
      String projectColorParam,
      int parentProjectId);

  SubProject deleteSubProject(int subProjectId);

  boolean addTaskToSubProject(int subProjectId, Task task);

  double getTotalHours(SubProject subProject);

  int getTotalPrice(SubProject subProject);
}
