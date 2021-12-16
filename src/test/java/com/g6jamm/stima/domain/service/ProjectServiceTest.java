package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.stub.ProjectRepositoryImpl;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Project;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/** @author Mathias */
class ProjectServiceTest {
  @Test
  public void createValidProjectTest() throws SystemException {
    ProjectService projectService = new ProjectService(new ProjectRepositoryImpl());
    Project project =
        projectService.createProject(
            "Test", LocalDate.parse("2021-01-01"), LocalDate.parse("2021-01-01"), "#000", null);

    Assertions.assertEquals(0, project.getId());
  }
}
