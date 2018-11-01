package cn.dankal.demo.ViewPagerHeaderMvp.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BasePresenter<V> {
  /*强引用（StrongReference）/ 软引用（SoftReference）
   / 弱引用（WeakReference）/ 虚引用（PhantomReference）
   当GC一但发现了弱引用对象，将会释放WeakReference所引用的对象。
  弱引用使用方法与软引用类似，但回收策略不同*/

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
