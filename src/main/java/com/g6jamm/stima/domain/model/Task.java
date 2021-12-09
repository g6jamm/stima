package com.g6jamm.stima.domain.model;

import java.time.LocalDate;

public class Task {

  private final String NAME;
  private final double HOURS;
  private final int PRICE;
  private final LocalDate START_DATE;
  private final LocalDate END_DATE;
  private final ResourceType RESOURCE_TYPE;
  private final int ID;

  private Task(TaskBuilder taskBuilder) {
    this.ID = taskBuilder.id;
    this.NAME = taskBuilder.name;
    this.HOURS = taskBuilder.hours;
    this.PRICE = taskBuilder.price;
    this.START_DATE = taskBuilder.startDate;
    this.END_DATE = taskBuilder.endDate;
    this.RESOURCE_TYPE = taskBuilder.resourceType;
  }

  public String getName() {
    return NAME;
  }

  public int getPrice() {
    return PRICE;
  }

  public double getHours() {
    return HOURS;
  }

  public LocalDate getEndDate() {
    return END_DATE;
  }

  public LocalDate getStartDate() {
    return START_DATE;
  }

  public int getId() {
    return ID;
  }

  public ResourceType getResourceType() {
    return RESOURCE_TYPE;
  }
  public double calculateWorkdays(){
    double workday = 7.4;
    double workdaysNeeded = HOURS/workday;
    return Math.round(workdaysNeeded);
  }

  public double calculateResources(){

    int workdaysAvailable = 5; //todo hvordan skal vi håndtere det?
    double resourcesNeeded = calculateWorkdays()/workdaysAvailable;

    return Math.round(resourcesNeeded);
  }


  public static class TaskBuilder {
    private int id;
    private String name;
    private double hours;
    private int price;
    private LocalDate startDate;
    private LocalDate endDate;
    private ResourceType resourceType;

    public TaskBuilder id(int id) {
      this.id = id;
      return this;
    }

    public TaskBuilder name(String name) {
      this.name = name;
      return this;
    }

    public TaskBuilder hours(double hours) {
      this.hours = hours;
      return this;
    }

    private TaskBuilder price() {
      if (this.resourceType == null) {
        this.price = 0;
      } else {
        this.price = (int) this.hours * this.resourceType.getPricePrHour(); // TODO rounding??
      }
      return this;
    }

    public TaskBuilder startDate(LocalDate startDate) {
      this.startDate = startDate;
      return this;
    }

    public TaskBuilder endDate(LocalDate endDate) {
      this.endDate = endDate;
      return this;
    }

    public TaskBuilder resourceType(ResourceType resourceType) {
      this.resourceType = resourceType;
      return this;
    }

    private void reset() {
      this.id = 0;
      this.name = null;
      this.hours = 0;
      this.price = 0;
      this.startDate = null;
      this.endDate = null;
      this.resourceType = null;
    }

    public Task build() {
      price();
      Task newTask = new Task(this);
      reset();
      return newTask;
    }
  }
}
