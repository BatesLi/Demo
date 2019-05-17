package cn.dankal.demo.Read.http.service;

import cn.dankal.demo.Read.bean.NewsListBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface TopNewsService {
  //http://c.m.163.com/nc/article/headline/T1348647909107/3-20.html
  //3是id值，用来判断返回是什么类型的数据的
  String API_TOPNEWS = "http://c.m.163.com/nc/article/";

  @GET("headline/T1348647909107/{id}-20.html") Observable<NewsListBean> fetchNews(@Path("id") int id);
}
