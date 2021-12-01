package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.model.Project;

public interface ProjectRepository {
  Project createProject();

  Project getProject(int projectId);

  Project deleteProject(int projectId);

  Project editProject(int projectId);

  Project getTotalHoursOfProject(int projectId);
}
