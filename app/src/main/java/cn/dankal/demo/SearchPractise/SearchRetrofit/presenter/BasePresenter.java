package cn.dankal.demo.SearchPractise.SearchRetrofit.presenter;

import cn.dankal.demo.SearchPractise.SearchRetrofit.view.IBaseView;

public abstract class BasePresenter<Gv extends IBaseView> {
  protected Gv mView;
}
