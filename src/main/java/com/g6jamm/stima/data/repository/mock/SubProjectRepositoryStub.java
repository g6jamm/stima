package com.g6jamm.stima.data.repository.mock;

import com.g6jamm.stima.data.repository.SubProjectRepository;
import com.g6jamm.stima.domain.model.SubProject;

import java.time.LocalDate;

public class SubProjectRepositoryStub implements SubProjectRepository {

  @Override
  public SubProject getSubProject(SubProject subProject) {
    return subProject;
  }

  @Override
  public SubProject createSubProject(String name, LocalDate startDate, LocalDate endDate) {

    return new SubProject.SubProjectBuilder()
        .name(name)
        .hours(0)
        .price(0)
        .tasks(null)
        .startDate(startDate)
        .endDate(endDate)
        .build();
  }

  @Override
  public SubProject deleteSubProject(SubProject subProject) {
    return subProject;
  }

  @Override
  public SubProject getTotalHours(SubProject subProject) {
    return null;
  }

  @Override
  public SubProject getTotalPrice(SubProject subProject) {
    return null;
  }
}
