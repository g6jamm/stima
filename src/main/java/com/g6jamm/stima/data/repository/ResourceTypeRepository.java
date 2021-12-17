package com.g6jamm.stima.data.repository;

/**
 * @author Mohamad, Andreas
 */

import com.g6jamm.stima.domain.exception.ResourceTypeNotFoundException;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.ResourceType;

import java.util.List;

public interface ResourceTypeRepository {

  List<ResourceType> getResourceTypes() throws SystemException;

  ResourceType getByResourceTypeName(String resourceTypeName) throws ResourceTypeNotFoundException;
}
