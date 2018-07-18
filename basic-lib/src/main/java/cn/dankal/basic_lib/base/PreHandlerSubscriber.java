package cn.dankal.basic_lib.base;


import android.util.Log;

import cn.dankal.basic_lib.constant.NetworkConstants;
import cn.dankal.basic_lib.exception.LocalException;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by leaflc on 2018/2/24.
 */

public abstract class PreHandlerSubscriber<T> implements Observer<T> {

    protected BaseView view;

    public PreHandlerSubscriber(BaseView view) {
        this.view = view;
    }


    @Override
    public void onError(Throwable e) {
        if (e != null) {
            if (e instanceof LocalException) {
                if (((LocalException) e).getErrorCode() == NetworkConstants.TOKEN_INVAILD) {
                    if (view != null) {
                        view.tokenInvalid();
                        view.showToast("登陆信息失效");
                    }
                } else {
                    LocalException localException = (LocalException) e;
                    view.showToast(localException.getMsg());
                }
            } else {
                Log.e("SubscriberThrowable", e.getMessage());
            }
        }
    }

    @Override
    public void onComplete() {
        this.view = null;
    }

    @Override
    public void onSubscribe(Disposable d) {
        view.addNetworkRequest(d);
    }
}
