package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Role;

import java.util.List;

public interface RoleRepository {

  List<Role> getRoles() throws SystemException;

  Role getRole(String roleName) throws SystemException;
}
