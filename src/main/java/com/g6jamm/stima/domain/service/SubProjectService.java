package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.SubProjectRepository;
import com.g6jamm.stima.domain.model.Project;
import com.g6jamm.stima.domain.model.ProjectLeaf;
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
  public ProjectLeaf createSubProject(
      String name,
      LocalDate startDate,
      LocalDate endDate,
      String projectColor,
      int parentProjectId) {
    return subProjectRepository.createSubProject(
        name, startDate, endDate, projectColor, parentProjectId);
  }

  /**
   * Gets all the subprojects from a project.
   *
   * @return List of subprojects
   * @author Jackie
   */
  public List<Project> getSubprojects(int projectId) {
    return subProjectRepository.getSubProjects(projectId); // WIP
  }

  /**
   * Get a subproject by id
   *
   * @param subProjectId
   * @return a sub project
   */
  public Project getSubProject(int subProjectId) {
    return subProjectRepository.getSubproject(subProjectId);
  }

  /**
   * Adding a task to sub project by id
   *
   * @param subProjectId
   * @param task
   * @return
   * @author Jackie
   */
  public boolean addTaskToSubProject(int subProjectId, Task task) {
    return subProjectRepository.addTaskToSubProject(subProjectId, task);
  }
}
