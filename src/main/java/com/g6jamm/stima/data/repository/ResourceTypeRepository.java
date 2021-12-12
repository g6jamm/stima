package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.exception.ResourceTypeNotFoundException;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.ResourceType;

import java.util.List;

public interface ResourceTypeRepository {

  public List<ResourceType> getResourceTypes() throws SystemException;

  public ResourceType findByName(String resourceTypeName) throws ResourceTypeNotFoundException;
}
