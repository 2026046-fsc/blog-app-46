package com.example.blog_app;

public class Blog {
  private Long id;
  private String user_name;
  private String title;
  private String content;

  public Blog(Long id, String user_name, String title, String content){
    this.id = id;
    this.user_name = user_name;
    this.title = title;
    this.content = content;
  }
  public Long getId() {
    return id;
  }
  public String getUser_name() {
    return user_name;
  }
  public void setUser_name(String user_name) {
    this.user_name = user_name;
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
