package cn.dankal.demo.SearchPractise.SearchWanAndroid;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.Api.WebContact;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.base.BaseActivity;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.custom.ProgressWebView;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.presenter.WebPresenter;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.just.agentwebX5.AgentWebX5;
import com.tencent.smtt.sdk.WebView;

/**
 * @author BatesLi
 * @date
 * @org
 * @email
 * @describe 页面的activity
 */

public class WebActivity extends BaseActivity implements WebContact.WebView {

    public WebContact.WebPresenter webPresenter;
  @BindView(R.id.title_bar_web) TitleBar titleBarWeb;
  @BindView(R.id.progress_web) ProgressWebView progressWeb;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_web);
    ButterKnife.bind(this);

    if (webPresenter == null) {
      webPresenter = WebPresenter.createPresenter(this);
    }
    initData();
    initView();
  }

  public void initData() {
    Intent intent = getIntent();
    String URL = intent.getStringExtra("URL");
    if (TextUtils.isEmpty(URL)) {
      URL = "http://www.baidu.com";
    }
    webPresenter.getData(URL);
  }

  public void initView() {
    titleBarWeb.setOnTitleBarListener(new OnTitleBarListener() {
      @Override public void onLeftClick(View v) {
        if (progressWeb.canGoBack()) {
          progressWeb.goBack();
        } else {
          finishActivity();
        }
      }

      @Override public void onTitleClick(View v) {

      }

      @Override public void onRightClick(View v) {

      }
    });
  }

  @Override public void setData(String url) {
    progressWeb.getSettings().setJavaScriptEnabled(true);
    progressWeb.loadUrl(url);
  }

  @Override public void setPresenter(WebContact.WebPresenter presenter) {
      this.webPresenter = presenter;
  }

  @Override
  public void onBackPressed() {
    if (progressWeb.canGoBack()) {
      progressWeb.goBack();
    } else {
      finishActivity();
    }
  }
}
