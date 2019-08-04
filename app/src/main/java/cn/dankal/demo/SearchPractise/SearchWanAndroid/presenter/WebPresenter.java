package cn.dankal.demo.SearchPractise.SearchWanAndroid.presenter;

import cn.dankal.demo.SearchPractise.SearchWanAndroid.Api.WebContact;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.base.BasePresenter;

public class WebPresenter extends BasePresenter implements WebContact.WebPresenter {

  public WebContact.WebView webView;

  public WebPresenter(WebContact.WebView webView) {
    this.webView = webView;
  }

  public static WebPresenter createPresenter(WebContact.WebView webView) {
    return new WebPresenter(webView);
  }

  @Override public void getData(String url) {
    webView.setData(url);
  }
}
