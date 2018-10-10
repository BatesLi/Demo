package cn.dankal.demo.ViewPagerHeaderMvp.bean;

import java.io.Serializable;

public class Category implements Serializable {

  private String image_lab;
  private String title;

  public String getImage_lab() {
    return image_lab;
  }

  public void setImage_lab(String image_lab) {
    this.image_lab = image_lab;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
