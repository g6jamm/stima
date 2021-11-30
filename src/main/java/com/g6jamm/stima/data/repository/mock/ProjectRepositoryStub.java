package com.g6jamm.stima.data.repository.mock;

import com.g6jamm.stima.data.repository.ProjectRepository;
import com.g6jamm.stima.domain.model.HeadProject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryStub implements ProjectRepository {

  @Override
  public HeadProject createProject() {

    return new HeadProject.ProjectBuilder()
        .projectId(1)
        .projectName("Demo")
        .startDate(LocalDate.of(2021, 1, 1))
        .endDate(LocalDate.of(2021, 1, 2))
        .totalPrice(20000)
        .totalHours(500.0)
        // .tasks()
        // .subProjects()
        .build();
  }

  @Override
  public HeadProject getProject(int projectId) {
    return null;
  }

  public List<HeadProject> getProjects() {
    List<HeadProject> headProjects = new ArrayList<>();

    headProjects.add(
        new HeadProject.ProjectBuilder()
            .projectId(2)
            .projectName("Projekt pink")
            .startDate(LocalDate.of(2021, 1, 1))
            .endDate(LocalDate.of(2021, 1, 2))
            .totalPrice(4000000)
            .totalHours(300)
            // .tasks()
            // .subProjects()
            .colorCode("pink")
            .build());

    headProjects.add(
        new HeadProject.ProjectBuilder()
            .projectId(1)
            .projectName("Projekt lilla")
            .startDate(LocalDate.of(2021, 1, 1))
            .endDate(LocalDate.of(2021, 1, 2))
            .totalPrice(7000000)
            .totalHours(800)
            // .tasks()
            // .subProjects()
            .colorCode("purple")
            .build());

    headProjects.add(
        new HeadProject.ProjectBuilder()
            .projectId(3)
            .projectName("Projekt grøn")
            .startDate(LocalDate.of(2021, 1, 1))
            .endDate(LocalDate.of(2021, 1, 2))
            .totalPrice(200000)
            .totalHours(60)
            // .tasks()
            // .subProjects()
            .colorCode("green")
            .build());

    headProjects.add(
        new HeadProject.ProjectBuilder()
            .projectId(3)
            .projectName("Projekt brun")
            .startDate(LocalDate.of(2021, 1, 1))
            .endDate(LocalDate.of(2021, 1, 2))
            .totalPrice(12000000)
            .totalHours(120)
            // .tasks()
            // .subProjects()
            .colorCode("brown")
            .build());

    return headProjects;
  }

  @Override
  public HeadProject deleteProject(int projectId) {
    return null;
  }

  @Override
  public HeadProject editProject(int projectId) {
    return null;
  }

  @Override
  public HeadProject getTotalHoursOfProject(int projectId) {
    return null;
  }
}
