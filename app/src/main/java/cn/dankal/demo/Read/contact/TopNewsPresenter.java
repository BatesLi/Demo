package cn.dankal.demo.Read.contact;

import cn.dankal.demo.Read.base.BaseView;
import cn.dankal.demo.Read.bean.NewsDetailBean;
import cn.dankal.demo.Read.bean.NewsListBean;

public interface TopNewsPresenter {

  interface View extends BaseView<NewsListBean>{
  }
  interface Presenter{
    void fetchTopNewsList(int id);//获取数据
  }
  interface ViewActivity extends View{
    void fetchActivityView(NewsDetailBean newsDetailBean);//请求网络数据
  }
}
