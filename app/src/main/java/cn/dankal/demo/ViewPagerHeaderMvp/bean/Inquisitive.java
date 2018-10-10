package cn.dankal.demo.ViewPagerHeaderMvp.bean;

import java.io.Serializable;
import java.util.List;

public class Inquisitive implements Serializable {

  private String image;
  private int type;
  private String headline_genre;
  private Post post;
  private List<HeadList> list;

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getHeadline_genre() {
    return headline_genre;
  }

  public void setHeadline_genre(String headline_genre) {
    this.headline_genre = headline_genre;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  public List<HeadList> getList() {
    return list;
  }

  public void setList(List<HeadList> list) {
    this.list = list;
  }
}
