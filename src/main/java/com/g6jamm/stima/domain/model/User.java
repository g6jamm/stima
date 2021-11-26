package com.g6jamm.stima.domain.model;

public class User {

  private final int ID;
  private final String FIRST_NAME;
  private final String LAST_NAME;
  private final String EMAIL;
  private final String PASSWORD;
  private final Role ROLE;  //TODO skal vi stadig bruge den?


  private User(UserBuilder userBuilder) {
    this.ID = userBuilder.id;
    this.FIRST_NAME = userBuilder.firstName;
    this.LAST_NAME = userBuilder.lastName;
    this.EMAIL = userBuilder.email;
    this.PASSWORD = userBuilder.password;
    this.ROLE = userBuilder.role;
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

  public Role getRole() {
    return ROLE;
  }

  public static class UserBuilder {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role; //TODO skal vi stadig bruge den?

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

    public UserBuilder role(Role role) {
      this.role = role;
      return this;
    }

    public User build(){
      User result = new User(this);
      return result;
    }
  }

}
