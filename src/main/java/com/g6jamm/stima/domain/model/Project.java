package com.g6jamm.stima.domain.model;

import java.time.LocalDate;
import java.util.List;

/**
 * @auther Mathias
 * */
public class Project {

  private final int PROJECT_ID;
  private final String PROJECT_NAME;
  private final double TOTAL_HOURS;
  private final int TOTAL_PRICE;
  private final LocalDate START_DATE;
  private final LocalDate END_DATE;
  private final List<Task> TASKS;
  private final List<SubProject> SUB_PROJECTS;
  private final String COLOR_CODE;

  private Project(ProjectBuilder projectBuilder) {
    this.PROJECT_ID = projectBuilder.projectId;
    this.PROJECT_NAME = projectBuilder.projectName;
    this.TOTAL_HOURS = projectBuilder.totalHours;
    this.TOTAL_PRICE = projectBuilder.totalPrice;
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
    return PROJECT_NAME;
  }

  public double getHours() {
    return TOTAL_HOURS;
  }

  public int getPrice() {
    return TOTAL_PRICE;
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

  public List<SubProject> getSubProjects() {
    return SUB_PROJECTS;
  }

  public String getColorCode() {
    return COLOR_CODE;
  }

  public static class ProjectBuilder {
    private int projectId;
    private String projectName;
    private double totalHours;
    private int totalPrice;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Task> tasks;
    private List<SubProject> subProjects;
    private String colorCode;

    public ProjectBuilder projectId(int projectId) {
      this.projectId = projectId;
      return this;
    }

    public ProjectBuilder projectName(String name) {
      this.projectName = name;
      return this;
    }

    public ProjectBuilder totalHours(double totalHours) {
      this.totalHours = totalHours;
      return this;
    }

    public ProjectBuilder totalPrice(int totalPrice) {
      this.totalPrice = totalPrice;
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

    public ProjectBuilder subProjects(List<SubProject> subProjects) {
      this.subProjects = subProjects;
      return this;
    }

    public ProjectBuilder colorCode(String colorCode) {
      this.colorCode = colorCode;
      return this;
    }

    private void reset() {
      this.projectId = 0;
      this.projectName = null;
      this.totalHours = 0;
      this.totalPrice = 0;
      this.startDate = null;
      this.endDate = null;
      this.tasks = null;
      this.subProjects = null;
      this.colorCode = null;
    }

    public Project build() {
      Project project = new Project(this);
      reset();
      return project;
    }
  }
}
