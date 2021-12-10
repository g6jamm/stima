package com.g6jamm.stima.domain.model;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class SubProject implements ProjectInterface {

  /**
   * Model for subproject
   *
   * @auther Jackie
   */
  private final int SUB_PROJECT_ID;

  private final String SUB_PROJECT_NAME;
  private final List<Task> SUB_PROJECT_TASKS;
  private final LocalDate START_DATE;
  private final LocalDate END_DATE;
  private final String COLOR_CODE;

  public SubProject(SubProjectBuilder subProjectBuilder) {
    this.SUB_PROJECT_ID = subProjectBuilder.subProjectId;
    this.SUB_PROJECT_NAME = subProjectBuilder.subProjectName;
    this.SUB_PROJECT_TASKS = subProjectBuilder.subProjectTasks;
    this.START_DATE = subProjectBuilder.startDate;
    this.END_DATE = subProjectBuilder.endDate;
    this.COLOR_CODE = subProjectBuilder.colorCode;
  }

  public List<Task> getTasks() {
    return this.SUB_PROJECT_TASKS;
  }

  public int getId() {
    return this.SUB_PROJECT_ID;
  }

  public String getName() {
    return this.SUB_PROJECT_NAME;
  }

  public LocalDate getStartDate() {
    return this.START_DATE;
  }

  public LocalDate getEndDate() {
    return this.END_DATE;
  }

  public double calculateHours() {
    double totalHours = 0.0;
    if (!SUB_PROJECT_TASKS.isEmpty()) {
      for (Task t : SUB_PROJECT_TASKS) {
        totalHours += t.getHours();
      }
    }
    return totalHours;
  }

  public int calculatePrice() {
    int totalPrice = 0;
    if (!SUB_PROJECT_TASKS.isEmpty()) {
      for (Task t : SUB_PROJECT_TASKS) {
        totalPrice += t.getPrice();
      }
    }
    return totalPrice;
  }

  public double calculateWorkdays() {
    double workday =
        7.4; // Workday in denmark is 7,4 hours if a workweek is 5 days and a workweek is 37 hours
    double workdaysNeeded = calculateHours() / workday;
    return Math.round(workdaysNeeded * 100) / 100;
  }

  public double calculateResources() {

    long workdaysAvailable =
        Duration.between(START_DATE.atStartOfDay(), END_DATE.atStartOfDay()).toDays()
            - 1; // todo hvordan skal vi håndtere det?
    double result = calculateWorkdays() / (workdaysAvailable);

    return Math.round(result * 100) / 100;
  }

  public String getColorCode() {
    return this.COLOR_CODE;
  }

  // TODO skal måske laves om
  public void addTask(Task task) {
    SUB_PROJECT_TASKS.add(task);
  }

  public static class SubProjectBuilder {
    private int subProjectId;
    private String subProjectName;
    private List<Task> subProjectTasks;
    private LocalDate startDate;
    private LocalDate endDate;
    private String colorCode;

    public SubProjectBuilder subProjectId(int subProjectId) {
      this.subProjectId = subProjectId;
      return this;
    }

    public SubProjectBuilder name(String name) {
      this.subProjectName = name;
      return this;
    }

    public SubProjectBuilder tasks(List<Task> tasks) {
      this.subProjectTasks = tasks;
      return this;
    }

    public SubProjectBuilder startDate(LocalDate startDate) {
      this.startDate = startDate;
      return this;
    }

    public SubProjectBuilder endDate(LocalDate endDate) {
      this.endDate = endDate;
      return this;
    }

    public SubProjectBuilder colorCode(String colorCode) {
      this.colorCode = colorCode;
      return this;
    }

    private void reset() {
      this.subProjectId = 0;
      this.subProjectName = null;
      this.subProjectTasks = null;
      this.startDate = null;
      this.endDate = null;
      this.colorCode = null;
    }

    public SubProject build() {
      SubProject newSubProject = new SubProject(this);
      reset();
      return newSubProject;
    }
  }
}
