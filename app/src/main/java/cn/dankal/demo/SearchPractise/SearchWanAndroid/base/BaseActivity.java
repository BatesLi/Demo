package cn.dankal.demo.SearchPractise.SearchWanAndroid.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import cn.dankal.demo.R;

public abstract class BaseActivity extends AppCompatActivity {

  static InputMethodManager inputMethodManager = null;
  public int height;
  protected BGASwipeBackHelper swipeBackHelper;
  private boolean isAllowScrRoate = false;

  @Override
  public void onCreate(Bundle savedInstanceSate) {
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
}
