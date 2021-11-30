package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.SubProjectRepository;
import com.g6jamm.stima.domain.model.SubProject;

import java.time.LocalDate;
import java.util.List;

public class SubProjectService {

  private final SubProjectRepository subProjectRepository;

  public SubProjectService(SubProjectRepository subProjectRepository) {
    this.subProjectRepository = subProjectRepository;
  }

  public SubProject createSubProject(String name, LocalDate startDate, LocalDate endDate) {
    return subProjectRepository.createSubProject(name, startDate, endDate);
  }

  public List<SubProject> getSubprojects() {
    return subProjectRepository.getSubProjects();
  }
}
