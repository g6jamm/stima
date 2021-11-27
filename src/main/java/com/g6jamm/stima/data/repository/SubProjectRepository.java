package com.g6jamm.stima.data.repository;

import com.g6jamm.stima.domain.model.SubProject;

public interface SubProjectRepository {

  SubProject getSubProject(SubProject project);

  SubProject createSubProject(SubProject project);

  SubProject deleteSubProject(SubProject project);

}
