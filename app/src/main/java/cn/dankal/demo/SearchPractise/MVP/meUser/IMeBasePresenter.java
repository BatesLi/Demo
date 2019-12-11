package cn.dankal.demo.SearchPractise.MVP.meUser;

public interface IMeBasePresenter<V> {
    void attachView(V view);

    void detachView();
}
