package com.g6jamm.stima.data.repository.stub;

import com.g6jamm.stima.data.repository.ResourceTypeRepository;
import com.g6jamm.stima.domain.exception.ResourceTypeNotFoundException;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.ResourceType;

import java.util.ArrayList;
import java.util.List;

public class ResourceTypeRepositoryImpl implements ResourceTypeRepository {

  private static final List<ResourceType> RESOURCE_TYPES = new ArrayList<>();

  /**
   * @auther Andreas
   */
  public ResourceTypeRepositoryImpl() {
    if (RESOURCE_TYPES.isEmpty()) {
      RESOURCE_TYPES.add(
          new ResourceType.ResourceTypeBuilder()
              .name("Senior Developer")
              .id(RESOURCE_TYPES.size() + 1)
              .pricePrHour(1250)
              .build());

      RESOURCE_TYPES.add(
          new ResourceType.ResourceTypeBuilder()
              .name("Project Manager")
              .id(RESOURCE_TYPES.size() + 1)
              .pricePrHour(1000)
              .build());
      RESOURCE_TYPES.add(
          new ResourceType.ResourceTypeBuilder()
              .name("Junior Developer")
              .id(RESOURCE_TYPES.size() + 1)
              .pricePrHour(800)
              .build());
    }
  }

  @Override
  public List<ResourceType> getResourceTypes() throws SystemException {
    return RESOURCE_TYPES;
  }

  @Override
  public ResourceType getByResourceTypeName(String resourceTypeName)
      throws ResourceTypeNotFoundException {
    for (ResourceType resourceType : RESOURCE_TYPES) {
      if (resourceType.getName().equals(resourceTypeName)) return resourceType;
    }
    throw new ResourceTypeNotFoundException("ResourceType does not exists");
  }
}
