package com.g6jamm.stima.data.repository.stub;

import com.g6jamm.stima.data.repository.SubProjectRepository;
import com.g6jamm.stima.domain.model.SubProject;
import com.g6jamm.stima.domain.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SubProjectRepositoryStub implements SubProjectRepository {

  private static List<SubProject> subProjects = new ArrayList<>();

  public SubProjectRepositoryStub() {
    if (subProjects.isEmpty()) {
      subProjects.add(
          new SubProject.SubProjectBuilder()
              .subProjectId(1)
              .name("example")
              .hours(100)
              .price(80000)
              .startDate(LocalDate.of(2020, 1, 1))
              .endDate(LocalDate.of(2021, 1, 1))
              .colorCode("green")
              .build());

      subProjects.add(
          new SubProject.SubProjectBuilder()
              .subProjectId(2)
              .name("example2")
              .hours(100)
              .price(80000)
              .startDate(LocalDate.of(2020, 1, 1))
              .endDate(LocalDate.of(2021, 1, 1))
              .colorCode("blue")
              .build());

      subProjects.add(
          new SubProject.SubProjectBuilder()
              .subProjectId(2)
              .name("example3")
              .hours(1)
              .price(500)
              .startDate(LocalDate.of(2020, 5, 1))
              .endDate(LocalDate.of(2021, 8, 1))
              .colorCode("red")
              .build());
    }
  }

  /**
   * @return List of Projects
   * @author Jackie
   */
  @Override
  public List<SubProject> getSubProjects() {
    return subProjects;
  }

  /**
   * Get a subproject by id
   *
   * @param subProjectId
   * @return a sub project
   * @author Jackie
   */
  @Override
  public SubProject getSubproject(int subProjectId) {

    for (SubProject sp : subProjects) {
      if (sp.getId() == subProjectId) {
        return sp;
      }
    }
    //TODO exception
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
  public SubProject createSubProject(String name, LocalDate startDate, LocalDate endDate) {

    SubProject subProject =
        new SubProject.SubProjectBuilder()
            .subProjectId(5) // TODO hardcorded, to test
            .name(name)
            // .hours(0)
            // .price(0)
            // .tasks(null)
            .startDate(startDate)
            .endDate(endDate)
            .build();

    subProjects.add(subProject);

    return subProject;
  }

  @Override
  public SubProject deleteSubProject(SubProject subProject) {
    return subProject;
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

    for (SubProject sp : subProjects) {
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
  public double getTotalHours(SubProject subProject) {
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
  public int getTotalPrice(SubProject subProject) {
    int result = 0;
    List<Task> tasks = subProject.getTasks();

    for (Task t : tasks) {
      result += t.getPrice();
    }

    return result;
  }
}
