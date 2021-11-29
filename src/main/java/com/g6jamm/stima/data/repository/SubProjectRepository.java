package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.model.SubProject;

import java.time.LocalDate;

public interface SubProjectRepository {

  SubProject getSubProject(int id);

  SubProject createSubProject(String name, LocalDate startDate, LocalDate endDate);

  SubProject deleteSubProject(SubProject subProject);

  double getTotalHours(SubProject subProject);

  int getTotalPrice(SubProject subProject);
}
