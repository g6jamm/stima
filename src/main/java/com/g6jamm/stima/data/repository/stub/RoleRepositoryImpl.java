package com.g6jamm.stima.data.repository.stub;

import com.g6jamm.stima.data.repository.RoleRepository;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.Role;

import java.util.List;

public class RoleRepositoryImpl implements RoleRepository {
  @Override
  public List<Role> getRoles() throws SystemException {
    return null;
  }

  @Override
  public Role findByName(String roleName) throws SystemException {
    return null;
  }
}
