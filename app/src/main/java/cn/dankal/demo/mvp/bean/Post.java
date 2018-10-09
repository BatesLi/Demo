package cn.dankal.demo.mvp.bean;

import java.io.Serializable;

public class Post implements Serializable {

  private String appview;
  private String title;
  private String id;
  private String description;
  private Category category;

  public String getAppview() {
    return appview;
  }

  public void setAppview(String appview) {
    this.appview = appview;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }
}
