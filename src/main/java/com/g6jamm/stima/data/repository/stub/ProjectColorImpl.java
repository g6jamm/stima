package com.g6jamm.stima.data.repository.stub;

import com.g6jamm.stima.data.repository.ProjectColorRepository;
import com.g6jamm.stima.domain.exception.SystemException;

import java.util.Map;

public class ProjectColorImpl implements ProjectColorRepository {
  /** @auther Mathias */
  private static final Map<String, String> PROJECT_COLORS =
      Map.of(
          "Light Carmine Pink",
          "#dc5b6e",
          "Royal Orange",
          "#f19748",
          "Sandstorm",
          "#ead04b",
          "Crayola's Forest Green",
          "#55a973",
          "Cyan Cornflower Blue",
          "#2d8fb6",
          "Royal Purple",
          "#6a54b4");

  public Map<String, String> getProjectColors() throws SystemException {
    return PROJECT_COLORS;
  }
}
