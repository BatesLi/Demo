package cn.dankal.demo.mvp.base;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import butterknife.ButterKnife;
import cn.dankal.basic_lib.util.ToastUtil;
import cn.dankal.demo.R;

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {

  protected T mPresenter;
  protected AppBarLayout mAppBar;
  protected Toolbar mToolbar;
  private SwipeRefreshLayout mRefreshLayout;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (createPresenter() != null) {
      mPresenter = createPresenter();
      mPresenter.attachView((V) this);
    }
    setContentView(provedContentViewId());
    ButterKnife.bind(this);

    mAppBar = findViewById(R.id.app_bar_layout);
    mToolbar = findViewById(R.id.toolbar);
    if (mToolbar != null && mAppBar != null) {
      setSupportActionBar(mToolbar);
      //返回箭头的逻辑
      if (canBack()) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
          actionBar.setDisplayHomeAsUpEnabled(true);
        }
      }
      //不知道什么意思的代码块
      /*if (Build.VERSION.SDK_INT >= 21) {
        mAppBar.setElevation(10.6f);//Z轴浮动
      }*/
    }
    if (isSetRefresh()) {
      setupSwipeRefresh();
    }
  }

  private void setupSwipeRefresh() {
    mRefreshLayout = findViewById(R.id.swipe_refresh);
    if (mRefreshLayout != null) {
      mRefreshLayout.setColorSchemeResources(R.color.refresh_progress_1,
          R.color.refresh_progress_2, R.color.refresh_progress_3);
      mRefreshLayout.setProgressViewOffset(true, 0, (int) TypedValue
          .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
      mRefreshLayout.setOnRefreshListener(this::requestDataRefresh);
    }
  }

  public void requestDataRefresh() {
  }

  public void setRefresh(boolean requestDataRefresh) {
    if (mRefreshLayout == null) {
      ToastUtil.toToast("数据为空");
    }
    if (!requestDataRefresh) {
      mRefreshLayout.postDelayed(() -> {
        if (mRefreshLayout != null) {
          mRefreshLayout.setRefreshing(false);
        }
      }, 1000);
    } else {
      mRefreshLayout.setRefreshing(true);
    }
  }

  public boolean canBack() {
    return false;
  }

  protected abstract T createPresenter();

  protected abstract int provedContentViewId();

  public boolean isSetRefresh() {
    return false;
  }
}
