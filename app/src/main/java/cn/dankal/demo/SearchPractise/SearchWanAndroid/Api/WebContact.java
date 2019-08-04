package cn.dankal.demo.SearchPractise.SearchWanAndroid.Api;

import cn.dankal.demo.SearchPractise.SearchWanAndroid.base.BaseView;

public interface WebContact {

  interface WebView extends BaseView<WebPresenter> {
    void setData(String url);
  }

  interface WebPresenter {
    void getData(String url);
  }
}
