package com.g6jamm.stima.data.repository.stub;

import com.g6jamm.stima.data.repository.SubProjectRepository;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Project;
import com.g6jamm.stima.domain.model.ProjectComposite;
import com.g6jamm.stima.domain.model.ProjectLeaf;
import com.g6jamm.stima.domain.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SubProjectRepositoryImpl implements SubProjectRepository {

  private static final List<Project> SUB_PROJECTS = new ArrayList<>();

  public SubProjectRepositoryImpl() {
    if (SUB_PROJECTS.isEmpty()) {
      SUB_PROJECTS.add(
          new ProjectLeaf.SubProjectBuilder()
              .subProjectId(SUB_PROJECTS.size() + 1)
              .name("example")
              .startDate(LocalDate.of(2020, 1, 1))
              .endDate(LocalDate.of(2021, 1, 1))
              .tasks(new ArrayList<Task>())
              .colorCode("green")
              .build());

      SUB_PROJECTS.add(
          new ProjectLeaf.SubProjectBuilder()
              .subProjectId(SUB_PROJECTS.size() + 1)
              .name("example2")
              .startDate(LocalDate.of(2020, 1, 1))
              .endDate(LocalDate.of(2021, 1, 1))
              .tasks(new ArrayList<Task>())
              .colorCode("blue")
              .build());

      SUB_PROJECTS.add(
          new ProjectLeaf.SubProjectBuilder()
              .subProjectId(SUB_PROJECTS.size() + 1)
              .name("example3")
              .startDate(LocalDate.of(2020, 5, 1))
              .endDate(LocalDate.of(2021, 8, 1))
              .tasks(new ArrayList<Task>())
              .colorCode("red")
              .build());
    }
  }

  /**
   * @return List of Projects
   * @author Jackie
   */
  @Override
  public List<Project> getSubProjects(int projectId) throws SystemException {
    return SUB_PROJECTS;
  }

  /**
   * Get a subproject by id
   *
   * @param subProjectId
   * @return a sub project
   * @author Jackie
   */
  @Override
  public Project getSubproject(int subProjectId) throws SystemException {

    for (Project sp : SUB_PROJECTS) {
      if (sp.getId() == subProjectId) {
        return sp;
      }
    }
    return null;
  }

  /**
   * Creates a sub project
   *
   * @param name
   * @param startDate
   * @param endDate
   * @return sub project
   * @author Jackie
   */
  @Override
  public ProjectLeaf createSubProject(
      String name, LocalDate startDate, LocalDate endDate, String projectColor, int parentProjectId)
      throws SystemException {

    ProjectLeaf subProject =
        new ProjectLeaf.SubProjectBuilder()
            .subProjectId(SUB_PROJECTS.size() + 1)
            .name(name)
            .tasks(new ArrayList<Task>())
            .startDate(startDate)
            .endDate(endDate)
            .colorCode(projectColor)
            .build();

    return subProject;
  }

  @Override
  public ProjectLeaf deleteSubProject(int subProjectId) {
    return null;
  }

  /**
   * Adding a task to sub project by id
   *
   * @param subProjectId
   * @param task
   * @return
   * @author Jackie
   */
  @Override
  public boolean addTaskToSubProject(int subProjectId, Task task) {

    for (Project sp : SUB_PROJECTS) {
      if (sp.getId() == subProjectId) {
        sp.addTask(task);
        return true;
      }
    }

    return false;
  }

  /**
   * Calculate the total hours from tasks in the subproject
   *
   * @param subProject
   * @return hours as double
   * @author Jackie
   */
  @Override
  public double getTotalHours(ProjectLeaf subProject) {
    int result = 0;
    List<Task> tasks = subProject.getTasks();

    for (Task t : tasks) {
      result += t.getHours();
    }

    return result;
  }

  /**
   * Calculate the total price from tasks in the subproject
   *
   * @param subProject
   * @return price as int
   * @author Jackie
   */
  @Override
  public int getTotalPrice(ProjectLeaf subProject) {
    int result = 0;
    List<Task> tasks = subProject.getTasks();

    for (Task t : tasks) {
      result += t.getPrice();
    }

    return result;
  }

  @Override
  public void editProject(ProjectComposite project) throws SystemException {}

  @Override
  public void deleteProject(int projectId) throws SystemException {}
}
