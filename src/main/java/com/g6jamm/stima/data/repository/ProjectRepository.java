package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.model.Project;

import java.time.LocalDate;
import java.util.List;

public interface ProjectRepository {
  Project createProject(String name, LocalDate startDate, LocalDate endDate, String projectColor);

  Project getProject(int projectId);

  Project deleteProject(int projectId);

  Project editProject(int projectId);

  Project getTotalHoursOfProject(int projectId);

  public List<Project> getProjects();
}
