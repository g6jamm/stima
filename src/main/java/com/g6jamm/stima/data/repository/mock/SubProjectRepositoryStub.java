package com.g6jamm.stima.data.repository.mock;

import com.g6jamm.stima.data.repository.SubProjectRepository;
import com.g6jamm.stima.domain.model.SubProject;
import org.springframework.scheduling.config.Task;

import java.time.LocalDate;

public class SubProjectRepositoryStub implements SubProjectRepository {


  @Override
  public SubProject getSubProject(SubProject project) {
    return null;
  }

  @Override
  public SubProject createSubProject(SubProject project) {
    project = new SubProject.SubProjectBuilder()
        .name("PROJECT GREEN")
        .hours(12.4).price(400)
        //.tasks(new Task()) //TODO add task
        .startDate(LocalDate.of(2022, 5, 6))
        .endDate(LocalDate.of(2022, 10, 21))
        .build();

    return project;
  }

  @Override
  public SubProject deleteSubProject(SubProject project) {
    return null;
  }
}
