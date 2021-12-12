package com.g6jamm.stima.data.repository.mysql;

import com.g6jamm.stima.data.repository.ResourceTypeRepository;
import com.g6jamm.stima.data.repository.util.DbManager;
import com.g6jamm.stima.domain.exception.ResourceTypeNotFoundException;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.ResourceType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResourceTypeRepositoryImpl implements ResourceTypeRepository {

  @Override
  public List<ResourceType> getResourceTypes() throws SystemException {
    List<ResourceType> resourceTypes = new ArrayList<>();

    try {
      String query = "SELECT * FROM resource_types";
      ResultSet rs = DbManager.getInstance().getConnection().prepareStatement(query).executeQuery();

      while (rs.next()) {
        ResourceType resourceType =
            new ResourceType.ResourceTypeBuilder()
                .name(rs.getString("name"))
                .pricePrHour(rs.getInt("price_per_hour"))
                .build();

        resourceTypes.add(resourceType);
      }

      return resourceTypes;

    } catch (SQLException e) {
      throw new SystemException(e);
    }
  }

  @Override
  public ResourceType findByName(String resourceTypeName) throws ResourceTypeNotFoundException {

    try {
      String query = "SELECT * FROM resource_types WHERE name = ?";

      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setString(1, resourceTypeName);
      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        return new ResourceType.ResourceTypeBuilder()
            .name(rs.getString("name"))
            .pricePrHour(rs.getInt("price_per_hour"))
            .build();
      }
    } catch (SQLException e) {
      throw new ResourceTypeNotFoundException("ResourceType does not exists");
    }
    return null;
  }
}
