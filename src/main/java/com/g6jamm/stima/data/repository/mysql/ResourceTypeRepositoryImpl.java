package com.g6jamm.stima.data.repository.mysql;

import com.g6jamm.stima.data.repository.ResourceTypeRepository;
import com.g6jamm.stima.data.repository.util.DbManager;
import com.g6jamm.stima.domain.exception.ResourceTypeNotFoundException;
import com.g6jamm.stima.domain.model.ResourceType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResourceTypeRepositoryImpl implements ResourceTypeRepository {

  private final List<ResourceType> RESOURCE_TYPES = new ArrayList();

  @Override
  public List<ResourceType> getResourceTypes() {
    return RESOURCE_TYPES;
  }

  @Override
  public void findByName(String resourceTypeName) throws ResourceTypeNotFoundException {

    try {
      String query = "SELECT * FROM resource_types WHERE name = ?";

      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setString(1, resourceTypeName);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        ResourceType obj =
            new ResourceType.ResourceTypeBuilder()
                .name(rs.getString("name"))
                .pricePrHour(rs.getInt("price_per_hour"))
                .build();

        RESOURCE_TYPES.add(obj);
      }

    } catch (SQLException e) {
      throw new ResourceTypeNotFoundException("ResourceType does not exists");
    }
  }
}
