package cn.dankal.demo.ViewPagerHeaderMvp.bean;

import java.util.List;

public class Response {

  private String has_more;
  private String last_key;
  private List<Inquisitive> feeds;
  private List<Inquisitive> banners;
  private List<Options> options;
  private Inquisitive headline;

  public int getListSize() {
    int size = 0;
    if (banners != null && feeds != null) {
      size = feeds.size() + 1;
    }
    if (headline.getPost() != null) {
      size = size + 1;
    }
    return size;
  }

  public String getHas_more() {
    return has_more;
  }

  public void setHas_more(String has_more) {
    this.has_more = has_more;
  }

  public String getLast_key() {
    return last_key;
  }

  public void setLast_key(String last_key) {
    this.last_key = last_key;
  }

  public List<Inquisitive> getFeeds() {
    return feeds;
  }

  public void setFeeds(List<Inquisitive> feeds) {
    this.feeds = feeds;
  }

  public List<Inquisitive> getBanners() {
    return banners;
  }

  public void setBanners(List<Inquisitive> banners) {
    this.banners = banners;
  }

  public List<Options> getOptions() {
    return options;
  }

  public void setOptions(List<Options> options) {
    this.options = options;
  }

  public Inquisitive getHeadline() {
    return headline;
  }

  public void setHeadline(Inquisitive headline) {
    this.headline = headline;
  }
}
