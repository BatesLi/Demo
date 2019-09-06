package cn.dankal.demo.SearchPractise.SearchWanAndroid;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import cn.dankal.basic_lib.util.ToastUtil;

public class DemoAndroid extends MultiDexApplication {

  private static DemoAndroid context;

  public static Context getContext() {
    return context;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    context = this;
    /**
     * 必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回
     * 第一个参数：应用程序上下文
     * 第二个参数：如果发现滑动返回后立即触摸界面时应用崩溃，请把该界面里比较特殊的 View 的 class 添加到该集合中，目前在库中已经添加了 WebView 和 SurfaceView
     */
    BGASwipeBackHelper.init(this, null);
    ToastUtil.register(context);
  }
}