package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.model.Project;

import java.util.List;

public interface ProjectRepository {
  Project createProject(Project project);

  Project getProject(int projectId);

  void deleteProject(int projectId);

  void editProject(Project project);

  List<Project> getProjects();
}
