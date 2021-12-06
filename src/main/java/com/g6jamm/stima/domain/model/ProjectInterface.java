package com.g6jamm.stima.domain.model;

import java.time.LocalDate;
import java.util.List;

public interface ProjectInterface {

    public LocalDate getStartDate();

    public LocalDate getEndDate();

    public List<Task> getTasks();
}
