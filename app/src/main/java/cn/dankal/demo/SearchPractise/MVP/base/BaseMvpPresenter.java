package cn.dankal.demo.SearchPractise.MVP.base;

public interface BaseMvpPresenter<T extends BaseMvpView> {
    void attach(T view);
}
