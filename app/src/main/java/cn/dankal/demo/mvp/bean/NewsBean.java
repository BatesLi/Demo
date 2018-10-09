package cn.dankal.demo.mvp.bean;

import java.util.List;

public class NewsBean {

  public List<News> todayNewsList;

  public NewsBean() {
  }

  public List<News> getTodayNewsList() {
    return todayNewsList;
  }

  public void setTodayNewsList(List<News> todayNewsList) {
    this.todayNewsList = todayNewsList;
  }

  public static class News {

    private String title;
    private String time;
    private int count;
    private int resId;

    public News(String title, int resId, String time, int count) {
      this.title = title;
      this.resId = resId;
      this.time = time;
      this.count = count;
    }

    public String getTime() {
      return time;
    }

    public void setTime(String time) {
      this.time = time;
    }

    public int getCount() {
      return count;
    }

    public void setCount(int count) {
      this.count = count;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public int getResId() {
      return resId;
    }

    public void setResId(int resId) {
      this.resId = resId;
    }
  }
}