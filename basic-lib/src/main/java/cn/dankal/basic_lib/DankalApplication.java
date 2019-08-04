package cn.dankal.basic_lib;

import android.app.Application;
import android.content.Context;
import cn.dankal.basic_lib.util.ToastUtil;

/**
 * description: application
 *
 * @author vane
 * @since 2018/3/12
 */

public class DankalApplication extends Application {


    private static DankalApplication context;

    public static final boolean IS_DEBUG_MODE = true;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        ToastUtil.register(context);
    }

    public static Context getContext() {
        return context;
    }
}
