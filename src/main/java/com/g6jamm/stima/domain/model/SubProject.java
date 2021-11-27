package com.g6jamm.stima.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class SubProject {

  private final String NAME;
  private final double HOURS;
  private final int PRICE;
//  private final ArrayList<Task> TASKS;
  private final LocalDate START_DATE;
  private final LocalDate END_DATE;

  public SubProject(SubProjectBuilder subProjectBuilder) {
    this.NAME = subProjectBuilder.name;
    this.HOURS = subProjectBuilder.hours;
    this.PRICE = subProjectBuilder.price;
//    this.TASKS = subProjectBuilder.tasks;
    this.START_DATE = subProjectBuilder.startDate;
    this.END_DATE = subProjectBuilder.endDate;
  }


  public static class SubProjectBuilder {
    private String name;
    private double hours;
    private int price;
//    private ArrayList<Task> tasks;
    private LocalDate startDate;
    private LocalDate endDate;

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

//    public SubProjectBuilder tasks(ArrayList<Task> tasks) {
//      this.tasks = tasks;
//      return this;
//    }

    public SubProjectBuilder startDate(LocalDate startDate) {
      this.startDate = startDate;
      return this;
    }

    public SubProjectBuilder endDate(LocalDate endDate) {
      this.endDate = endDate;
      return this;
    }

    private void reset() {
      this.name = null;
      this.hours = 0.0;
      this.price = 0;
//      this.tasks = null;
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
