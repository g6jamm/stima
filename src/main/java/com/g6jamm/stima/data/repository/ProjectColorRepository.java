package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.exception.SystemException;

import java.util.Map;

public interface ProjectColorRepository {

  Map<String, String> getProjectColors() throws SystemException;
}
