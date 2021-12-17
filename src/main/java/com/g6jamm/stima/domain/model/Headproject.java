package com.g6jamm.stima.domain.model;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

/** @author Mathias */
public class Headproject implements Project {

  private final int PROJECT_ID;
  private final String NAME;
  private final LocalDate START_DATE;
  private final LocalDate END_DATE;
  private final List<Task> TASKS;
  private final List<Project> SUB_PROJECTS;
  private final String COLOR_CODE;

  private Headproject(ProjectBuilder projectBuilder) {
    this.PROJECT_ID = projectBuilder.projectId;
    this.NAME = projectBuilder.name;
    this.START_DATE = projectBuilder.startDate;
    this.END_DATE = projectBuilder.endDate;
    this.TASKS = projectBuilder.tasks;
    this.SUB_PROJECTS = projectBuilder.subProjects;
    this.COLOR_CODE = projectBuilder.colorCode;
  }

  public int getId() {
    return PROJECT_ID;
  }

  public String getName() {
    return NAME;
  }

  public LocalDate getStartDate() {
    return START_DATE;
  }

  public LocalDate getEndDate() {
    return END_DATE;
  }

  public List<Task> getTasks() {
    return TASKS;
  }

  public List<Project> getSubProjects() {
    return SUB_PROJECTS;
  }

  public String getColorCode() {
    return COLOR_CODE;
  }

  /**
   * Method for calculating total hours of a project. Loops through subprojects and tasks to find
   * the total. May be called recursively.
   *
   * @author Andreas
   */
  public double calculateHours() {
    double totalHours = 0.0;
    if (!SUB_PROJECTS.isEmpty()) {
      for (Project subProject : SUB_PROJECTS) {
        totalHours += subProject.calculateHours();
      }
    }
    if (!TASKS.isEmpty()) {
      for (Task t : TASKS) {
        totalHours += t.getHours();
      }
    }
    return totalHours;
  }

  /**
   * Method for calculating total price of a project. Loops through subprojects and tasks to find
   * the total. May be called recursively.
   *
   * @auther Mathias, Andreas
   */
  public int calculatePrice() {
    int totalPrice = 0;
    if (!SUB_PROJECTS.isEmpty()) {
      for (Project subProject : SUB_PROJECTS) {
        totalPrice += subProject.calculatePrice();
      }
    }
    if (!TASKS.isEmpty()) {
      for (Task t : TASKS) {
        totalPrice += t.getPrice();
      }
    }
    return totalPrice;
  }

  public double calculateWorkdays() {
    double workday =
        7.4; // Workday in denmark is 7,4 hours if a workweek is 5 days and a workweek is 37 hours
    double workdaysNeeded = calculateHours() / workday;
    return Math.round(workdaysNeeded * 100.0) / 100.0;
  }

  /**
   * Returns different in days, include start and end date.
   *
   * @auther Mathias
   */
  public long calculateDays() {
    return Duration.between(START_DATE.atStartOfDay(), END_DATE.atStartOfDay()).toDays() + 1;
  }

  /** @auther Mathias, Jakie */
  public void addSubProject(Project project) {
    SUB_PROJECTS.add(project);
  }

  public double calculateResources() {

    long workdaysAvailable =
        Duration.between(START_DATE.atStartOfDay(), END_DATE.atStartOfDay()).toDays() + 1;
    double result = calculateWorkdays() / (workdaysAvailable);

    return Math.round(result * 100.0) / 100.0;
  }

  public void addSubProject(Subproject subproject) {
    SUB_PROJECTS.add(subproject);
  }

  public void addTask(Task task) {
    TASKS.add(task);
  }

  public static class ProjectBuilder {
    private int projectId;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Task> tasks;
    private List<Project> subProjects;
    private String colorCode;

    public ProjectBuilder projectId(int projectId) {
      this.projectId = projectId;
      return this;
    }

    public ProjectBuilder projectName(String name) {
      this.name = name;
      return this;
    }

    public ProjectBuilder startDate(LocalDate startDate) {
      this.startDate = startDate;
      return this;
    }

    public ProjectBuilder endDate(LocalDate endDate) {
      this.endDate = endDate;
      return this;
    }

    public ProjectBuilder tasks(List<Task> tasks) {
      this.tasks = tasks;
      return this;
    }

    public ProjectBuilder subProjects(List<Project> subProjects) {
      this.subProjects = subProjects;
      return this;
    }

    public ProjectBuilder colorCode(String colorCode) {
      this.colorCode = colorCode;
      return this;
    }

    /**
     * Method to reset variables in the builder. Added in order to avoid having a variable hanging
     * from a previous use.
     *
     * @author Andreas
     */
    private void reset() {
      this.projectId = 0;
      this.name = null;
      this.startDate = null;
      this.endDate = null;
      this.tasks = null;
      this.subProjects = null;
      this.colorCode = null;
    }

    /**
     * Returns a Headproject object. The director can construct several product variations using the
     * same building steps.
     */
    public Headproject build() {
      Headproject headProject = new Headproject(this);
      reset();
      return headProject;
    }
  }
}
