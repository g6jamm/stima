package com.g6jamm.stima.domain.model;

import java.time.LocalDate;
import java.util.List;

public class SubProject {

  /**
   * Model for subproject
   *
   * @auther Jackie
   * */
  private final int SUB_PROJECT_ID;
  private final String SUB_PROJECT_NAME;
  private final double TOTAL_HOURS;
  private final int TOTAL_PRICE;
  private final List<Task> SUB_PROJECT_TASKS;
  private final LocalDate START_DATE;
  private final LocalDate END_DATE;
  private final String COLOR_CODE;

  public SubProject(SubProjectBuilder subProjectBuilder) {
    this.SUB_PROJECT_ID = subProjectBuilder.subProjectId;
    this.SUB_PROJECT_NAME = subProjectBuilder.subProjectName;
    this.TOTAL_HOURS = subProjectBuilder.totalHours;
    this.TOTAL_PRICE = subProjectBuilder.totalPrice;
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

  public double getHours() {
    return this.TOTAL_HOURS;
  }

  public int getPrice() {
    return this.TOTAL_PRICE;
  }

  public String getColorCode() { return this.COLOR_CODE; }

  //TODO skal måske laves om
  public void addTask(Task task) {
    SUB_PROJECT_TASKS.add(task);
  }

  public static class SubProjectBuilder {
    private int subProjectId;
    private String subProjectName;
    private double totalHours;
    private int totalPrice;
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

    public SubProjectBuilder hours(double hours) {
      this.totalHours = hours;
      return this;
    }

    public SubProjectBuilder price(int price) {
      this.totalPrice = price;
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
      this.totalHours = 0.0;
      this.totalPrice = 0;
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
