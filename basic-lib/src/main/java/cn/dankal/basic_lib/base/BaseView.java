package cn.dankal.basic_lib.base;

import io.reactivex.disposables.Disposable;

/**
 * description: MVP模式中V层接口
 *
 * @author vane
 * @since 2018/1/30
 */

public interface BaseView {
    /**
     * 显示等待进度条弹窗
     */
    void showLoadingDialog();

    /**
     * 销毁等待进度条弹窗
     */
    void dismissLoadingDialog();

    /**
     * 显示Toast
     *
     * @param message Toast 消息
     */
    void showToast(String message);

    /**
     * 登录信息失效
     */
    void tokenInvalid();


    /**
     * 添加网络请求
     *
     * @param d onSubscribe(Disposable d)
     */
    void addNetworkRequest(Disposable d);
}
