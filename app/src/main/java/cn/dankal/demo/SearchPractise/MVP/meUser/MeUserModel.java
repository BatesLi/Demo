package cn.dankal.demo.SearchPractise.MVP.meUser;

import android.os.Handler;
import android.os.Looper;

public class MeUserModel implements IMeUserModel {

    Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void getLogin(String user, String pwd, callBack callBack) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if ("kenan".equals(user) && "123456".equals(pwd)) {
                    callBack.Success();
                } else {
                    callBack.onFailure("账号或者密码错误");
                }
            }
        }, 2000);
    }
}
