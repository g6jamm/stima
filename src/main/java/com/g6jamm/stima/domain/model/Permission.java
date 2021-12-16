package com.g6jamm.stima.domain.model;

public class Permission {

  private final String NAME;
  private final int ID;

  public Permission(PermissionBuilder permissionBuilder) {
    this.ID = permissionBuilder.id;
    this.NAME = permissionBuilder.name;
  }

  public int getId() {
    return ID;
  }

  public String getName() {
    return NAME;
  }

  public static class PermissionBuilder {
    private String name;
    private int id;

    public PermissionBuilder name(String name) {
      this.name = name;
      return this;
    }

    public PermissionBuilder id(int id) {
      this.id = id;
      return this;
    }

    public void reset() {
      this.name = null;
      this.id = 0;
    }

    public Permission build() {
      Permission permission = new Permission(this);
      reset();
      return permission;
    }
  }
}
