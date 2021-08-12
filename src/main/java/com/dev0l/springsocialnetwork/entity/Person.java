package com.dev0l.springsocialnetwork.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Person {

  private String firstname;
  private String lastname;
  private String email;
  private String address;

  public Person() {
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

}
