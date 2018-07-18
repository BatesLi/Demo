package cn.dankal.manager;

import android.content.Context;
import android.content.SharedPreferences;

import cn.dankal.basic_lib.DankalApplication;
import cn.dankal.basic_lib.pojo.LoginResponse;
import cn.dankal.basic_lib.util.PreferenceUtil;

/**
 * @author leaflc
 */
public class UserManager {
    private static LoginResponse mLoginResponse;
    private static Context mContext = DankalApplication.getContext();
    public static SharedPreferences mSp;
    private static final String PreferenceName = "userinfo";

    static {
        mSp = mContext.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE);
    }


    /**
     * 最初mLoginResponse各属性内容为空，
     * 如登录后可更新当前的LoginResponse和本地的缓存
     */
    public static void saveUserInfo(LoginResponse LoginResponse) {
        if (LoginResponse == null) {
            return;
        }
        PreferenceUtil.updateBean(mSp, mLoginResponse, LoginResponse);
    }

    /**
     * 如果从本地缓存中获取对象为空则实例化一个空对象
     * 判断是否登录全程通过user_id是不是为0来判断而不是通过
     * mLoginResponse是否等于null，防止UserManager.getLoginResponse出现空指针
     */
    private static void readUserInfo() {
        mLoginResponse = (LoginResponse) PreferenceUtil.getBeanValue(mSp, LoginResponse.class);
        if (mLoginResponse == null) {
            mLoginResponse = new LoginResponse();
        }
    }

    /**
     * 清空缓存时调用
     */
    public static void resetUserInfo() {
        SharedPreferences.Editor editor = mSp.edit();
        editor.clear();
        editor.apply();
        mLoginResponse = new LoginResponse();
    }

    /**
     * 判断没有登录
     *
     * @return
     */
    public static boolean unLogined() {
        if (getUserInfo().getAccessToken() != null &&
                !getUserInfo().getAccessToken().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public static LoginResponse getUserInfo() {
        if (mLoginResponse == null) {
            readUserInfo();
        }
        return mLoginResponse;
    }
}
