package com.dev0l.springsocialnetwork.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Message {

  private String title;
  private String content;

  public Message() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
