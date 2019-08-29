package cn.dankal.demo.SearchPractise.SearchWanAndroid.presenter;

import android.app.Activity;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.Api.LoadTasksCallBack;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.Api.WanAndoridLoginContact;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.Api.WebTask;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.Utils.StringUtils;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.base.BasePresenter;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.model.Data;

public class WanAndroidLoginPresenter extends BasePresenter
    implements WanAndoridLoginContact.LoginPresenter,
    LoadTasksCallBack<Data> {

  public WebTask mWebTask;
  public WanAndoridLoginContact.LoginView mLoginView;

  public WanAndroidLoginPresenter(WanAndoridLoginContact.LoginView loginView, WebTask webTask) {
    this.mLoginView = loginView;
    this.mWebTask = webTask;
  }

  public static WanAndroidLoginPresenter createWanAndroidLoginPresenter(
      WanAndoridLoginContact.LoginView loginView
      , WebTask webTask) {
    return new WanAndroidLoginPresenter(loginView, webTask);
  }

  @Override public void onSuccess(Data data, int... params) {
    //mLoginView.LoginSuccess(data);
  }

  @Override public void onStart() {

  }

  @Override public void onFailed() {

  }

  @Override public void onError(int code, String msg) {
    mLoginView.setRegister(msg);
  }

  @Override public void onFinish() {

  }

  @Override public void getLogin(String username, String password) {
    mWebTask.execute(this, StringUtils.TYPE_LOGIN, username, password);
  }

  @Override public void getRegister(String username, String password) {
    mWebTask.execute(this, StringUtils.TYPE_REGISTER, username, password);
  }

  @Override public void resetLoginLocation(Activity activity) {

  }
}
