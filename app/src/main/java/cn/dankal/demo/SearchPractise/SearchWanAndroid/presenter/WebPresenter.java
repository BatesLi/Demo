package cn.dankal.demo.SearchPractise.SearchWanAndroid.presenter;

import cn.dankal.demo.SearchPractise.SearchWanAndroid.Api.WebContact;

public class WebPresenter implements WebContact.WebPresenter {

    public static WebContact.WebView mWebView;

  public static WebPresenter createPresenter(WebContact.WebView webView) {
      mWebView = webView;
      return new WebPresenter();
  }

  @Override public void getData(String url) {
      mWebView.setData(url);
  }
}
