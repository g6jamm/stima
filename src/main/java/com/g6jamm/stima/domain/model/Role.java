package com.g6jamm.stima.domain.model;

/** @auther Mohamad */
public class Role {

  private final String NAME;
  private final int ID;

  public Role(RoleBuilder roleBuilder) {
    this.ID = roleBuilder.id;
    this.NAME = roleBuilder.name;
  }

  public int getId() {
    return ID;
  }

  public String getName() {
    return NAME;
  }

  public static class RoleBuilder {
    private String name;
    private int id;

    public RoleBuilder name(String name) {
      this.name = name;
      return this;
    }

    public RoleBuilder id(int id) {
      this.id = id;
      return this;
    }

    /**
     * Method to reset variables in the builder. Added in order to avoid having a variable hanging
     * from a previous use.
     */
    public void reset() {
      this.name = null;
      this.id = 0;
    }

    /**
     * Returns a Role object. The director can construct several product variations using the same
     * building steps.
     */
    public Role build() {
      Role role = new Role(this);
      reset();
      return role;
    }
  }
}
