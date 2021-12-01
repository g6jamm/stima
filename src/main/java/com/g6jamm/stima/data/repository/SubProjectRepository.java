package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.model.SubProject;

import java.time.LocalDate;
import java.util.List;

public interface SubProjectRepository {

  /** @author Jackie */
  List<SubProject> getSubProjects();

  SubProject createSubProject(String name, LocalDate startDate, LocalDate endDate);

  SubProject deleteSubProject(SubProject subProject);

  double getTotalHours(SubProject subProject);

  int getTotalPrice(SubProject subProject);
}
