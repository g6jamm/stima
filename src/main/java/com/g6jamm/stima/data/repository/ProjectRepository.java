package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.model.HeadProject;

public interface ProjectRepository {
  HeadProject createProject();

  HeadProject getProject(int projectId);

  HeadProject deleteProject(int projectId);

  HeadProject editProject(int projectId);

  HeadProject getTotalHoursOfProject(int projectId);
}
