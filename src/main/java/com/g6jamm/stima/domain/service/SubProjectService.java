package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.SubProjectRepository;
import com.g6jamm.stima.domain.model.SubProject;
import com.g6jamm.stima.domain.model.Task;

import java.time.LocalDate;
import java.util.List;

public class SubProjectService {

  private final SubProjectRepository subProjectRepository;

  public SubProjectService(SubProjectRepository subProjectRepository) {
    this.subProjectRepository = subProjectRepository;
  }

  /**
   * Generate a subproject, with start parameter. hours = 0, price = 0
   *
   * @param name
   * @param startDate
   * @param endDate
   * @return
   * @author Jackie
   */
  public SubProject createSubProject(String name, LocalDate startDate, LocalDate endDate) {
    return subProjectRepository.createSubProject(name, startDate, endDate);
  }

  /**
   * Gets all the subprojects from a project.
   *
   * @return List of subprojects
   * @author Jackie
   */
  public List<SubProject> getSubprojects() { // TODO add reference to project
    return subProjectRepository.getSubProjects();
  }

  /**
   * Get a subproject by id
   *
   * @param subProjectId
   * @return a sub project
   */
  public SubProject getSubProject(int subProjectId) { return subProjectRepository.getSubproject(subProjectId); }

  public boolean addTaskToSubProject(int subProjectId, Task task) {
    return subProjectRepository.addTaskToSubProject(subProjectId, task);
  }

}
