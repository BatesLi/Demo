package cn.dankal.demo.user;

import cn.dankal.basic_lib.base.BasePresenter;
import cn.dankal.basic_lib.base.BaseView;

/**
 * @author leaflc
 */
public interface LoginContact {

    interface LoginView extends BaseView {

        void loginSuccess();

        void getVerifyCodeSuccess(String tempCode);

    }


    interface LoginPresenter extends BasePresenter<LoginView> {

        void login(String phoneNum, String verifyCode,String tempCode);

        void getVerifyCode(String phoneNum);
    }
}
