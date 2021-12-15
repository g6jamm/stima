package com.g6jamm.stima.domain.service;

import com.g6jamm.stima.data.repository.stub.ProjectColorImpl;
import com.g6jamm.stima.domain.exception.SystemException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class ProjectColorServiceTest {

  @Test
  void testIfColorExistInList() throws SystemException {
    ProjectColorService projectColorService = new ProjectColorService(new ProjectColorImpl());

    Map<String, String> result = projectColorService.getProjectColors();

    Assertions.assertTrue(result.containsKey("Light Carmine Pink"));
  }

  @Test
  void testLookupColorHexadecimalByName() throws SystemException {
    ProjectColorService projectColorService = new ProjectColorService(new ProjectColorImpl());

    Map<String, String> result = projectColorService.getProjectColors();

    Assertions.assertEquals("#dc5b6e", result.get("Light Carmine Pink"));
  }
}
