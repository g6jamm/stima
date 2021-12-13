package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Permission;


import java.util.List;

public interface PermissionRepository {

  public List<Permission> getPermissions() throws SystemException;

  public Permission findByName(String permissionName) throws SystemException;
}
