package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.SubProjectRepository;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Project;
import com.g6jamm.stima.domain.model.ProjectComposite;
import com.g6jamm.stima.domain.model.ProjectLeaf;
import com.g6jamm.stima.domain.model.Task;

import java.time.LocalDate;
import java.util.List;

public class SubProjectService {

  private final SubProjectRepository SUB_PROJECT_REPOSITORY;

  public SubProjectService(SubProjectRepository subProjectRepository) {
    this.SUB_PROJECT_REPOSITORY = subProjectRepository;
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
      String name, LocalDate startDate, LocalDate endDate, String projectColor, int parentProjectId)
      throws SystemException {
    return SUB_PROJECT_REPOSITORY.createSubProject(
        name, startDate, endDate, "1", parentProjectId); // TODO: send object med & fix color
  }

  /**
   * Gets all the subprojects from a project.
   *
   * @return List of subprojects
   * @author Jackie
   */
  public List<Project> getSubprojects(int projectId) throws SystemException {
    return SUB_PROJECT_REPOSITORY.getSubProjects(projectId); // WIP
  }

  /**
   * Get a subproject by id
   *
   * @param subProjectId
   * @return a sub project
   */
  public Project getSubProject(int subProjectId) throws SystemException {
    return SUB_PROJECT_REPOSITORY.getSubproject(subProjectId);
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
    return SUB_PROJECT_REPOSITORY.addTaskToSubProject(subProjectId, task);
  }

  public void editProject(
      int projectId, String name, LocalDate startDate, LocalDate endDate, String projectColor)
      throws SystemException {

    ProjectComposite subproject =
        new ProjectComposite.ProjectBuilder()
            .projectId(projectId)
            .projectName(name)
            .startDate(startDate)
            .endDate(endDate)
            .colorCode("1") // TODO: @Jacky
            .build();

    SUB_PROJECT_REPOSITORY.editProject(subproject);
  }

  public void deleteProject(int projectId) throws SystemException {
    SUB_PROJECT_REPOSITORY.deleteProject(projectId);
  }
}
