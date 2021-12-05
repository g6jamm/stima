package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.model.Project;

import java.time.LocalDate;

public interface ProjectRepository {
  Project createProject(String name, LocalDate startDate, LocalDate endDate, String projectColor);

  Project getProject(int projectId);

  Project deleteProject(int projectId);

  Project editProject(int projectId);

  Project getTotalHoursOfProject(int projectId);
}
