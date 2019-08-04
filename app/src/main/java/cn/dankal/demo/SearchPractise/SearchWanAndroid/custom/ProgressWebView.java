package cn.dankal.demo.SearchPractise.SearchWanAndroid.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import cn.dankal.demo.R;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class ProgressWebView extends WebView {

  private ProgressBar progressBar;//进度条
  private int progressHeight = 5;//进度条默认为 10px
  private OnWebViewListener onWebViewListener;

  public ProgressWebView(Context context) {
    super(context);
  }

  public ProgressWebView(Context context, AttributeSet attributeSet) {
    super(context, attributeSet);
  }

  private void initView(Context context) {
    //开启js脚本支持
    getSettings().setJavaScriptEnabled(true);

    progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
    //设置加载进度条的高度
    progressBar.setLayoutParams(
        new AbsoluteLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT
            , progressHeight, 0, 0));

    Drawable drawable = context.getResources().getDrawable(R.drawable.web_progress_bar);
    progressBar.setProgressDrawable(drawable);
    //添加进度条到WebView
    addView(progressBar);

    //适配手机大小
    getSettings().setUseWideViewPort(true);
    getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
    getSettings().setLoadWithOverviewMode(true);
    getSettings().setSupportZoom(true);
    getSettings().setBuiltInZoomControls(true);
    getSettings().setDisplayZoomControls(false);

    setWebChromeClient(new WVChromeClient());
    //setWebViewClient(new WVClient());
  }

  public void setOnWebViewListener(OnWebViewListener onWebViewListener) {
    this.onWebViewListener = onWebViewListener;
  }

  //进度回调接口
  public interface OnWebViewListener {
    void onProgressChanged(WebView webView, int newProgress);

    void onPageFinish(WebView webView);
  }

  private class WVChromeClient extends WebChromeClient {
    int progressPrice = 100;//进度值（0->16->30->100）就返回这四次数字

    @Override
    public void onProgressChanged(WebView webView, int newProgress) {
      if (newProgress == progressPrice) {
        progressBar.setVisibility(GONE);
      } else {
        if (progressBar.getVisibility() == GONE) {
          progressBar.setVisibility(VISIBLE);
          progressBar.setProgress(newProgress);
        }
      }
      if (onWebViewListener != null) {
        onWebViewListener.onProgressChanged(webView, newProgress);
      }
      super.onProgressChanged(webView, newProgress);
    }
  }

  private class WVClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
      webView.loadUrl(url);
      return true;
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
      //忽略证书问题
      handler.proceed();
    }

    @Override
    public void onPageFinished(WebView webView, String url) {
      progressBar.setVisibility(GONE);
      if (onWebViewListener != null) {
        onWebViewListener.onPageFinish(webView);
      }
      super.onPageFinished(webView, url);
    }
  }
}





