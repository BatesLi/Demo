package cn.dankal.demo.SearchPractise.SearchWanAndroid.Api;

import android.app.Activity;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.base.BaseView;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.model.Data;

public interface WanAndoridLoginContact {

  interface LoginView extends BaseView<LoginPresenter> {
    void LoginSuccess(Data data);

    void setRegister(String... info);

    void setLoginLocation(int height);

    void refreshLocation(int height);
  }

  interface LoginPresenter {
    void getLogin(String username, String password);

    void getRegister(String username, String password);

    void resetLoginLocation(Activity activity);
  }
}
