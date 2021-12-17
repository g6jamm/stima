package com.g6jamm.stima.domain.model;

/** @auther Mohamad */
public class User {

  private final int ID;
  private final String FIRST_NAME;
  private final String LAST_NAME;
  private final String EMAIL;
  private final String PASSWORD;
  private final ResourceType RESOURCETYPE;
  private final Permission PERMISSION;

  private User(UserBuilder userBuilder) {
    this.ID = userBuilder.id;
    this.FIRST_NAME = userBuilder.firstName;
    this.LAST_NAME = userBuilder.lastName;
    this.EMAIL = userBuilder.email;
    this.PASSWORD = userBuilder.password;
    this.RESOURCETYPE = userBuilder.resourceType;
    this.PERMISSION = userBuilder.permission;
  }

  public int getId() {
    return ID;
  }

  public String getFirstName() {
    return FIRST_NAME;
  }

  public String getLastName() {
    return LAST_NAME;
  }

  public String getEmail() {
    return EMAIL;
  }

  public String getPassword() {
    return PASSWORD;
  }

  public ResourceType getResourceType() {
    return RESOURCETYPE;
  }

  public Permission getPermission() {
    return PERMISSION;
  }

  public static class UserBuilder {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ResourceType resourceType;
    private Permission permission;

    public UserBuilder id(int id) {
      this.id = id;
      return this;
    }

    public UserBuilder firstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    public UserBuilder lastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    public UserBuilder email(String email) {
      this.email = email;
      return this;
    }

    public UserBuilder password(String password) {
      this.password = password;
      return this;
    }

    public UserBuilder resourceType(ResourceType resourceType) {
      this.resourceType = resourceType;
      return this;
    }

    public UserBuilder permission(Permission permission) {
      this.permission = permission;
      return this;
    }

    /**
     * Method to reset variables in the builder. Added in order to avoid having a variable hanging
     * from a previous use.
     */
    private void reset() {
      this.id = 0;
      this.firstName = null;
      this.lastName = null;
      this.email = null;
      this.password = null;
      this.resourceType = null;
      this.permission = null;
    }

    public User build() {
      User result = new User(this);
      reset();
      return result;
    }
  }
}
