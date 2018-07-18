package cn.dankal.demo.user;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

import cn.dankal.basic_lib.base.BaseApi;
import cn.dankal.basic_lib.base.PreHandlerDialogSubscriber;
import cn.dankal.basic_lib.base.PreHandlerSubscriber;
import cn.dankal.basic_lib.exception.ExceptionHandle;
import cn.dankal.basic_lib.pojo.LoginResponse;
import cn.dankal.basic_lib.util.MatcherUtils;
import cn.dankal.manager.UserManager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author leaflc
 */
public class LoginPresenter implements LoginContact.LoginPresenter {

    private LoginContact.LoginView loginView;

    @Override
    public void login(String phoneNum, String verifyCode, String tempCode) {
        if (MatcherUtils.checkPhone(phoneNum) && !verifyCode.isEmpty()) {
            Map<String, String> params = new HashMap<>(3);
            params.put("mobile", phoneNum);
            params.put("code", verifyCode);
            params.put("temp_code", tempCode);
            BaseApi.getRetrofit().create(UserService.class)
                    .signIn(params)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .onErrorResumeNext(t -> {
                        return Observable.error(ExceptionHandle.handleException(t));
                    })
                    .subscribe(new PreHandlerDialogSubscriber<LoginResponse>(loginView) {
                        @Override
                        public void onNext(LoginResponse loginResponse) {
                            UserManager.saveUserInfo(loginResponse);
                            loginView.loginSuccess();
                        }
                    });
        }
    }

    @Override
    public void getVerifyCode(String phoneNum) {
        if (MatcherUtils.checkPhone(phoneNum)) {
            BaseApi.getRetrofit().create(UserService.class)
                    .getVerifyCode(phoneNum)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .onErrorResumeNext(t -> {
                        return Observable.error(ExceptionHandle.handleException(t));
                    })
                    .subscribe(new PreHandlerDialogSubscriber<String>(loginView) {
                        @Override
                        public void onNext(String s) {
                            loginView.getVerifyCodeSuccess(JSON.parseObject(s).getString("temp_code"));
                        }
                    });
        } else {
            loginView.showToast("请输入正确的手机号");
        }

    }

    @Override
    public void attachView(@NonNull LoginContact.LoginView view) {
        loginView = view;
    }

    @Override
    public void detachView() {
        loginView = null;
    }
}
