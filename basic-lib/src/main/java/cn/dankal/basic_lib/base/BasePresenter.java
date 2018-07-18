package cn.dankal.basic_lib.base;

import android.support.annotation.NonNull;

/**
 * @author leaflc
 */
public interface BasePresenter <T extends BaseView> {

    /**
     * View 启动时
     *
     * @param view class extends BaseView
     */
    void attachView(@NonNull T view);

    /**
     * View 销毁时
     */
    void detachView();
}
