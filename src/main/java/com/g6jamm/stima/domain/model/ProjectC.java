package com.g6jamm.stima.domain.model;

import java.time.LocalDate;
import java.util.List;

public interface ProjectC {

    List<Task> getTasks();

    int getId();

    String getName();

    LocalDate getStartDate();

    LocalDate getEndDate();

    double calculateHours();

    int calculatePrice();

    String getColorCode();

    void addTask(Task task);

}
