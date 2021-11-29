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
  public SubProject createSubProject(SubProject subProject) {
    subProject =
        new SubProject.SubProjectBuilder()
            .name("PROJECT GREEN")
            .hours(12.4)
            .price(400)
            .tasks(null) // TODO add task
            .startDate(LocalDate.of(2022, 5, 6))
            .endDate(LocalDate.of(2022, 10, 21))
            .build();

    return subProject;
  }

  @Override
  public SubProject deleteSubProject(SubProject subProject) {
    return subProject;
  }
}
