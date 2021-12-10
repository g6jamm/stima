package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.model.ProjectComposite;

import java.util.List;

public interface ProjectRepository {
  ProjectComposite createProject(ProjectComposite project);

  void editProject(ProjectComposite project);

  ProjectComposite getProject(int projectId);

  void deleteProject(int projectId);

  List<ProjectComposite> getProjects();
}
