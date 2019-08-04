package cn.dankal.demo.SearchPractise.SearchWanAndroid.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import cn.dankal.demo.R;

/**
 *
 */
public abstract class BaseActivity extends AppCompatActivity
    implements BGASwipeBackHelper.Delegate {

  static InputMethodManager inputMethodManager = null;
  public int height;
  protected BGASwipeBackHelper swipeBackHelper;
  private boolean isAllowScrRoate = false;

  @Override
  public void onCreate(Bundle savedInstanceSate) {
    initSwipeBackFinish();
    super.onCreate(savedInstanceSate);
  }

  @Override
  public void onDestroy() {
    System.gc();
    super.onDestroy();
  }

  public void startActivity(Class<?> className) {
    startActivity(new Intent(this, className));
    /*
     * R.anim.enter_fade_out:新的Activity进入时的动画，这里是指OtherActivity进入时的动画
     * R.anim.enter_fade_in：旧的Activity出去时的动画，这里是指this进入时的动画
     * */
    overridePendingTransition(R.anim.enter_fade_out, R.anim.enter_fade_in);
  }

  public void startActivityWithoutAnimation(Class<?> className) {
    startActivity(new Intent(BaseActivity.this, className));
  }

  public void finishActivity() {
    finish();
    overridePendingTransition(R.anim.enter_fade_out, R.anim.enter_fade_in);
  }

  public void setScreenRoate(boolean isAllowScrRoate) {
    this.isAllowScrRoate = isAllowScrRoate;
    if (isAllowScrRoate) {
    } else {
      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    System.gc();
    super.onActivityResult(requestCode, resultCode, data);
  }

  /**
   * 初始化滑动返回
   */
  public void initSwipeBackFinish() {
    swipeBackHelper = new BGASwipeBackHelper(this, this);
    // 设置滑动返回是否可用。默认值为 true
    swipeBackHelper.setSwipeBackEnable(true);
    // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
    swipeBackHelper.setIsOnlyTrackingLeftEdge(true);
    // 设置是否是微信滑动返回样式。默认值为 true
    swipeBackHelper.setIsWeChatStyle(true);
    // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
    swipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
    // 设置是否显示滑动返回的阴影效果。默认值为 true
    swipeBackHelper.setIsNeedShowShadow(true);
    // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
    swipeBackHelper.setIsShadowAlphaGradient(true);
    // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
    swipeBackHelper.setSwipeBackThreshold(0.3f);
    // 设置底部导航条是否悬浮在内容上，默认值为 false
    swipeBackHelper.setIsNavigationBarOverlap(false);
  }

  /**
   * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
   */
  @Override
  public boolean isSupportSwipeBack() {
    return true;
  }

  /**
   * 正在滑动返回
   *
   * @param slideOffset 从 0 到 1
   */
  @Override
  public void onSwipeBackLayoutSlide(float slideOffset) {
  }

  /**
   * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
   */
  @Override
  public void onSwipeBackLayoutCancel() {
  }

  /**
   * 滑动返回执行完毕，销毁当前 Activity
   */
  @Override
  public void onSwipeBackLayoutExecuted() {
    swipeBackHelper.swipeBackward();
  }

  @Override
  public void onBackPressed() {
    // 正在滑动返回的时候取消返回按钮事件
    if (swipeBackHelper.isSliding()) {
      return;
    }
    swipeBackHelper.backward();
  }
}
