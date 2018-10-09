package cn.dankal.demo.mvp.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import cn.dankal.basic_lib.util.ToastUtil;
import cn.dankal.demo.R;

public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {

  protected T mPresenter;
  private SwipeRefreshLayout mRefreshLayout;

  @Override
  public void onCreate(Bundle savedInstance) {
    super.onCreate(savedInstance);
    mPresenter = createPresenter();
    //mPresenter.attachView((V)this);
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(createViewLayout(), container, false);
    ButterKnife.bind(this, view);
    initView(view);
    if (isRefresh()) {
      setupSwipeRefresh(view);
    }
    return view;
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    mPresenter.detachView();
  }

  private void setupSwipeRefresh(View view) {
    mRefreshLayout = view.findViewById(R.id.swipe_refresh);
    if (mRefreshLayout != null) {
     /* mRefreshLayout.setColorSchemeColors(R.color.refresh_progress_1,
          R.color.refresh_progress_2,R.color.refresh_progress_3);*/
      mRefreshLayout.setProgressViewOffset(true, 0, (int) TypedValue
          .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
      mRefreshLayout.setOnRefreshListener(this::onDestroy);
    }
  }

  public void requestDataRefresh() {
  }

  protected abstract T createPresenter();

  protected abstract int createViewLayout();

  protected abstract View initView(View view);

  public boolean isRefresh() {
    return true;
  }

  public void setRefresh(boolean requestDataRefresh) {
    if (mRefreshLayout == null) {
      ToastUtil.toToast("数据为空");
      return;
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
}
