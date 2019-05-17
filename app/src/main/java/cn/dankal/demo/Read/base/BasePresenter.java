package cn.dankal.demo.Read.base;

import cn.dankal.demo.Read.app.AppConstants;
import cn.dankal.demo.Read.http.LifeSubscription;
import cn.dankal.demo.Read.http.Stateful;
import cn.dankal.demo.Read.utils.Callback;
import cn.dankal.demo.Read.utils.HttpUtils;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;
import rx.Observable;

public class BasePresenter<T extends BaseView> {
  //WeakReference(弱引用)指代引用对象本身

  protected Reference<T> mReferenceView;//指页面
  protected T mView;
  private Callback callback;

  public void attachView(LifeSubscription lifeSubscription) {
    this.mReferenceView = new WeakReference<>((T)mReferenceView);
    //如果此方法为空, 那么说明mReferenceView指向的对象已经被回收了
    mView = mReferenceView.get();
  }
  protected<T> void invoke(Observable<T> observable,Callback<T> callback) {
    this.callback = callback;
    HttpUtils.invoke((LifeSubscription) mView,observable,callback);
  }
  //检查子类集合是否为空
  public void checkState(List list) {
    if (list.size() == 0) {
        if (mView instanceof Stateful) {
          ((Stateful) mView).setState(AppConstants.STATE_EMPTY);
          return;
        }
    }
  }
  public void detachView() {
    if (mReferenceView != null) {
        mReferenceView.clear();
        mReferenceView = null;
      if (mView != null)
          mView = null;
      if (callback != null) {
          callback.detachView();
      }
    }
  }
}
