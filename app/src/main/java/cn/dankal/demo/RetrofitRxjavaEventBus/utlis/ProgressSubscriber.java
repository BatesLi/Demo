package cn.dankal.demo.RetrofitRxjavaEventBus.utlis;

import android.content.Context;
import cn.dankal.basic_lib.util.ToastUtil;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import rx.Subscriber;

public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {

  private SubscriberOnNextListener subscriberOnNextListener;
  private Context context;
  private ProgressDialogHandler mProgressDialogHandler;

  public ProgressSubscriber(SubscriberOnNextListener subscriberOnNextListener, Context context) {
    this.subscriberOnNextListener = subscriberOnNextListener;
    this.context = context;

    mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
  }

  private void showProgressDialog() {
    if (mProgressDialogHandler != null) {
      mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG)
          .sendToTarget();
    }
  }

  private void dismissProgressDialog() {
    if (mProgressDialogHandler != null) {
      mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG)
          .sendToTarget();
      mProgressDialogHandler = null;
    }
  }

  @Override
  public void onStart() {
    showProgressDialog();
  }

  @Override public void onCompleted() {
    dismissProgressDialog();
  }

  @Override public void onError(Throwable e) {
    if (e instanceof SocketTimeoutException) {
      ToastUtil.toToast("网络请求超时，请检测你的网络状态");
    } else if (e instanceof ConnectException) {
      ToastUtil.toToast("网络请求超时，请检测你的网络状态");
    } else {
      ToastUtil.toToast("error" + e.getMessage());
    }
  }

  @Override public void onNext(T t) {
    if (subscriberOnNextListener != null) {
      subscriberOnNextListener.onNext(t);
    }
  }

  //希望当cancel掉ProgressDialog的时候，能够取消订阅，也就取消了当前的Http请求
  @Override public void onCancelProgress() {
    if (!this.isUnsubscribed()) {
      this.unsubscribe();
    }
  }
}
