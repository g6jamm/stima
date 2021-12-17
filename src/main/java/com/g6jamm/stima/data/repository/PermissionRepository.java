package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Permission;

import java.util.List;

/** @author Mohamad */
public interface PermissionRepository {

  List<Permission> getPermissions() throws SystemException;

  Permission getPermission(String permissionName) throws SystemException;
}
