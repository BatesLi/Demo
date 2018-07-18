package cn.dankal.basic_lib.base;


import io.reactivex.disposables.Disposable;


/**
 * @author Dankal Android Developers
 */
public abstract class PreHandlerDialogSubscriber<T> extends PreHandlerSubscriber<T> {

    public PreHandlerDialogSubscriber(BaseView view) {
        super(view);
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (view != null) {
            view.dismissLoadingDialog();
        }
    }


    @Override
    public void onComplete() {
        if (view != null) {
            view.dismissLoadingDialog();
        }
        super.onComplete();
    }

    @Override
    public void onSubscribe(Disposable d) {
        super.onSubscribe(d);
        if (view != null) {
            view.showLoadingDialog();
        }
    }
}
