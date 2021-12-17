package com.g6jamm.stima.domain.model;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

/**
 * @auther Jakie
 */
public class Subproject implements Project {

  private final int ID;

  private final String NAME;
  private final List<Task> TASKS;
  private final LocalDate START_DATE;
  private final LocalDate END_DATE;
  private final String COLOR_CODE;

  public Subproject(SubProjectBuilder subProjectBuilder) {
    this.ID = subProjectBuilder.id;
    this.NAME = subProjectBuilder.name;
    this.TASKS = subProjectBuilder.tasks;
    this.START_DATE = subProjectBuilder.startDate;
    this.END_DATE = subProjectBuilder.endDate;
    this.COLOR_CODE = subProjectBuilder.colorCode;
  }

  public List<Task> getTasks() {
    return this.TASKS;
  }

  public int getId() {
    return this.ID;
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

  public double calculateHours() {
    double totalHours = 0.0;
    if (!TASKS.isEmpty()) {
      for (Task t : TASKS) {
        totalHours += t.getHours();
      }
    }
    return totalHours;
  }

  public int calculatePrice() {
    int totalPrice = 0;
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
    return Math.round(workdaysNeeded * 100) / 100;
  }

  public double calculateResources() {

    long workdaysAvailable =
        Duration.between(START_DATE.atStartOfDay(), END_DATE.atStartOfDay()).toDays() + 1;
    double result = calculateWorkdays() / (workdaysAvailable);

    return Math.round(result * 100) / 100;
  }

  public long calculateDays() {
    return Duration.between(START_DATE.atStartOfDay(), END_DATE.atStartOfDay()).toDays() + 1;
  }

  public String getColorCode() {
    return this.COLOR_CODE;
  }

  public void addTask(Task task) {
    TASKS.add(task);
  }

  public static class SubProjectBuilder {
    private int id;
    private String name;
    private List<Task> tasks;
    private LocalDate startDate;
    private LocalDate endDate;
    private String colorCode;

    public SubProjectBuilder subProjectId(int subProjectId) {
      this.id = subProjectId;
      return this;
    }

    public SubProjectBuilder name(String name) {
      this.name = name;
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

    public SubProjectBuilder colorCode(String colorCode) {
      this.colorCode = colorCode;
      return this;
    }

    /**
     * Method to reset variables in the builder. Added in order to avoid having a variable hanging
     * from a previous use.
     */
    private void reset() {
      this.id = 0;
      this.name = null;
      this.tasks = null;
      this.startDate = null;
      this.endDate = null;
      this.colorCode = null;
    }

    /**
     * Returns a Subproject object.
     * The director can construct several product variations using the same building steps.
     */
    public Subproject build() {
      Subproject newSubProject = new Subproject(this);
      reset();
      return newSubProject;
    }
  }
}

