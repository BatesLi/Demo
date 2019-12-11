package cn.dankal.demo.SearchPractise.MVP.EasyLogin;

import android.os.Handler;
import android.os.Looper;

public class EasyLoginModel implements IEasyLoginModel {

    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void easyLogin(String name, String password, CallBack callBack) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if ("kenan".equals(name) && "123456".equals(password)) {
                    callBack.onSuccess();
                } else {
                    callBack.onFailure("登录失败");
                }
            }
        }, 2000);
    }
}
