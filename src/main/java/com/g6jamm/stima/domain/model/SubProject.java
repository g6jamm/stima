package com.g6jamm.stima.domain.model;

import java.time.LocalDate;
import java.util.List;

public class SubProject {

  /** @auther Jackie */
  private final int SUB_PROJECT_ID;
  private final String NAME;
  private final double HOURS;
  private final int PRICE;
  private final List<Task> TASKS;
  private final LocalDate START_DATE;
  private final LocalDate END_DATE;

  public SubProject(SubProjectBuilder subProjectBuilder) {
    this.SUB_PROJECT_ID = subProjectBuilder.subProjectId;
    this.NAME = subProjectBuilder.name;
    this.HOURS = subProjectBuilder.hours;
    this.PRICE = subProjectBuilder.price;
    this.TASKS = subProjectBuilder.tasks;
    this.START_DATE = subProjectBuilder.startDate;
    this.END_DATE = subProjectBuilder.endDate;
  }

  public List<Task> getTasks() {
    return this.TASKS;
  }

  public int getId() {
    return this.SUB_PROJECT_ID;
  }

  public String getName() {
    return this.NAME;
  }

  public LocalDate getStartDate() {
    return this.START_DATE;
  }

  public LocalDate getEndDate() {
    return this.END_DATE;
  }

  public double getHours() {
    return this.HOURS;
  }

  public int getPrice() {
    return this.PRICE;
  }

  public static class SubProjectBuilder {
    private int subProjectId;
    private String name;
    private double hours;
    private int price;
    private List<Task> tasks;
    private LocalDate startDate;
    private LocalDate endDate;

    public SubProjectBuilder subProjectId(int subProjectId) {
      this.subProjectId = subProjectId;
      return this;
    }

    public SubProjectBuilder name(String name) {
      this.name = name;
      return this;
    }

    public SubProjectBuilder hours(double hours) {
      this.hours = hours;
      return this;
    }

    public SubProjectBuilder price(int price) {
      this.price = price;
      return this;
    }

    public SubProjectBuilder tasks(List<Task> tasks) {
      this.tasks = tasks;
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

    private void reset() {
      this.subProjectId = 0;
      this.name = null;
      this.hours = 0.0;
      this.price = 0;
      this.tasks = null;
      this.startDate = null;
      this.endDate = null;
    }

    public SubProject build() {
      SubProject newSubProject = new SubProject(this);
      reset();
      return newSubProject;
    }
  }
}
