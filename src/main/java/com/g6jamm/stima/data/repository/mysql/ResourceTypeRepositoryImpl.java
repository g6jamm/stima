package com.g6jamm.stima.data.repository.mysql;

import com.g6jamm.stima.data.repository.ResourceTypeRepository;
import com.g6jamm.stima.data.repository.mysql.util.DbManager;
import com.g6jamm.stima.domain.exception.ResourceTypeNotFoundException;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.ResourceType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** @auther Mathias */
public class ResourceTypeRepositoryImpl implements ResourceTypeRepository {

  /**
   * Returns a list of resource types.
   *
   * @author Mathias, Mohamad
   */
  @Override
  public List<ResourceType> getResourceTypes() throws SystemException {
    List<ResourceType> resourceTypes = new ArrayList<>();

    try {
      String query = "SELECT * FROM resource_types";
      ResultSet rs = DbManager.getInstance().getConnection().prepareStatement(query).executeQuery();

      while (rs.next()) {

        ResourceType resourceType =
            new ResourceType.ResourceTypeBuilder()
                .id(rs.getInt("resource_type_id"))
                .pricePrHour(rs.getInt("price_per_hour"))
                .name(rs.getString("name"))
                .build();

        resourceTypes.add(resourceType);
      }

      return resourceTypes;

    } catch (SQLException e) {
      throw new SystemException(e);
    }
  }

  /**
   * Returns a specific resource type by name.
   *
   * @author Mathias, Mohamad
   */
  @Override
  public ResourceType getByResourceTypeName(String resourceTypeName)
      throws ResourceTypeNotFoundException {

    try {
      String query = "SELECT * FROM resource_types WHERE name = ?";

      PreparedStatement ps = DbManager.getInstance().getConnection().prepareStatement(query);
      ps.setString(1, resourceTypeName);
      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        return new ResourceType.ResourceTypeBuilder()
            .id(rs.getInt("resource_type_id"))
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
