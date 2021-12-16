package com.g6jamm.stima.data.repository.stub;

import com.g6jamm.stima.data.repository.SubProjectRepository;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Headproject;
import com.g6jamm.stima.domain.model.Project;
import com.g6jamm.stima.domain.model.Subproject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SubProjectRepositoryImpl implements SubProjectRepository {

  private static final List<Project> SUB_PROJECTS = new ArrayList<>();

  /** @auther Jackie */
  public SubProjectRepositoryImpl() {
    if (SUB_PROJECTS.isEmpty()) {
      SUB_PROJECTS.add(
          new Subproject.SubProjectBuilder()
              .subProjectId(SUB_PROJECTS.size() + 1)
              .name("example")
              .startDate(LocalDate.of(2020, 1, 1))
              .endDate(LocalDate.of(2021, 1, 1))
              .tasks(new ArrayList<>())
              .colorCode("green")
              .build());

      SUB_PROJECTS.add(
          new Subproject.SubProjectBuilder()
              .subProjectId(SUB_PROJECTS.size() + 1)
              .name("example2")
              .startDate(LocalDate.of(2020, 1, 1))
              .endDate(LocalDate.of(2021, 1, 1))
              .tasks(new ArrayList<>())
              .colorCode("blue")
              .build());

      SUB_PROJECTS.add(
          new Subproject.SubProjectBuilder()
              .subProjectId(SUB_PROJECTS.size() + 1)
              .name("example3")
              .startDate(LocalDate.of(2020, 5, 1))
              .endDate(LocalDate.of(2021, 8, 1))
              .tasks(new ArrayList<>())
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
   * Creates a sub project
   *
   * @return sub project
   * @author Jackie
   */
  @Override
  public Subproject createSubProject(
      String name, LocalDate startDate, LocalDate endDate, String projectColor, int parentProjectId)
      throws SystemException {

    return new Subproject.SubProjectBuilder()
        .subProjectId(SUB_PROJECTS.size() + 1)
        .name(name)
        .tasks(new ArrayList<>())
        .startDate(startDate)
        .endDate(endDate)
        .colorCode(projectColor)
        .build();
  }

  @Override
  public void editProject(Project project) throws SystemException {}

  @Override
  public void deleteProject(int projectId) throws SystemException {}
}
