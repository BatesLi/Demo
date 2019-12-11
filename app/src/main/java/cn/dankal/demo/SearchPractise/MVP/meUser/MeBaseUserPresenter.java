package cn.dankal.demo.SearchPractise.MVP.meUser;

/**
 * @author
 * @date
 * @org
 * @email
 * @describe 主要是处理P层的一些错误，例如当View销毁的时候P层依旧在调用View的方法，会导致出现空指针错误
 */

public class MeBaseUserPresenter<V extends IMeBaseView> implements IMeBasePresenter<V> {

    private V mMeBaseUserView;

    public V getMeBaseUserView() {
        return mMeBaseUserView;
    }

    @Override
    public void attachView(V view) {
        this.mMeBaseUserView = view;
    }

    @Override
    public void detachView() {
        mMeBaseUserView = null;
    }

    //判断view是否为null
    public boolean isMeBaseUserView() {
        return mMeBaseUserView != null;
    }

    //(如果view为null那么，就抛出一个错误：证明view被销毁了，view与P之间没有联系)
    // 检查view与presenter是否连接
    public void checkViewAttach() {
        if (!isMeBaseUserView()) {
            throw new MeBaseUserViewNotAttachedException();
        }
    }

    //自定义一个异常
    public static class MeBaseUserViewNotAttachedException extends RuntimeException {
        public MeBaseUserViewNotAttachedException() {
            super("请求数据前请先调用 attachView(view) 方法与View建立连接");
        }
    }
}
