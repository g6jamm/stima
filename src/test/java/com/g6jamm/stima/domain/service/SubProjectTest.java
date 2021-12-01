package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.stub.SubProjectRepositoryStub;
import com.g6jamm.stima.domain.model.SubProject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class SubProjectTest {

  @Test
  public void createRightSubprojectTest() {
    SubProjectService sunProjectService =
        new SubProjectService(new SubProjectRepositoryStub());
    SubProject subProject = sunProjectService.createSubProject("Test", LocalDate.of(2020, 5, 5), LocalDate.of(2021, 5, 5));

    Assertions.assertEquals(5, subProject.getId());
  }

  @Test
  public void createWrongSubProjectTest() {
//    SubProjectService sunProjectService =
//        new SubProjectService(new SubProjectRepositoryStub());
//    SubProject subProject = sunProjectService.createSubProject("Test", LocalDate.of(2020, 5, 5), LocalDate.of(2021, 5, 5));
//
//    Assertions.assertEquals(,);
  }

  @Test
  public void calcHoursTest() {

  }

  @Test
  public void calcPriceTest() {

  }


}
