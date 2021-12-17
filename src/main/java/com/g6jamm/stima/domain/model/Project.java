package com.g6jamm.stima.domain.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface for project, Here we use a design Pattern called Composite Pattern to handle both
 * HeadProject and Subproject the same. There by creating polymophic behaviour and saving code.
 *
 * @author Andreas
 */
public interface Project {

  List<Task> getTasks();

  int getId();

  String getName();

  LocalDate getStartDate();

  LocalDate getEndDate();

  double calculateHours();

  int calculatePrice();

  String getColorCode();

  void addTask(Task task);

  double calculateWorkdays();
}
