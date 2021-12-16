package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Headproject;
import com.g6jamm.stima.domain.model.User;

import java.util.List;

public interface ProjectRepository {
  Headproject createProject(Headproject project, User user) throws SystemException;

  void editProject(Headproject project) throws SystemException;

  void deleteProject(int projectId) throws SystemException;

  List<Headproject> getProjects(User user) throws SystemException;
}
