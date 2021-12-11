package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.ProjectComposite;
import com.g6jamm.stima.domain.model.User;

import java.util.List;

public interface ProjectRepository {
  ProjectComposite createProject(ProjectComposite project, User user);

  void editProject(ProjectComposite project);

  ProjectComposite getProject(int projectId) throws SystemException;

  void deleteProject(int projectId);

  List<ProjectComposite> getProjects(User user) throws SystemException;
}
