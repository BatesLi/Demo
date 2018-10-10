package cn.dankal.demo.ViewPagerHeaderMvp.bean;

import java.io.Serializable;

public class HeadList implements Serializable {

  private String description;
  private String title;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
