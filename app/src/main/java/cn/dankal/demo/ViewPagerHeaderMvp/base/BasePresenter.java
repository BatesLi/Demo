package cn.dankal.demo.ViewPagerHeaderMvp.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BasePresenter<V> {

  protected Reference<V> mRefView;

  public void attachView(V view) {
    mRefView = new WeakReference<V>(view);
  }

  public boolean isAttachView() {
    return mRefView != null && mRefView.get() != null;
  }

  public V getView() {
    return mRefView.get();
  }

  public void detachView() {
    if (mRefView != null) {
      mRefView.clear();
      mRefView = null;
    }
  }
}
