package com.g6jamm.stima.data.repository.stub;

import com.g6jamm.stima.data.repository.PermissionRepository;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Permission;

import java.util.List;

public class PermissionRepositoryImpl implements PermissionRepository {
  @Override
  public List<Permission> getPermissions() throws SystemException {
    return null;
  }

  @Override
  public Permission findByName(String permissionName) throws SystemException {
    return null;
  }
}
