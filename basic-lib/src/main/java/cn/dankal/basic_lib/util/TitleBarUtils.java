package cn.dankal.basic_lib.util;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import cn.dankal.basic_lib.R;

/**
 * description: 标题栏配置工具类
 *
 * @author vane
 * @since 2018/1/30
 */

public class TitleBarUtils {

    public static View init(Activity activity, int layoutResId) {
        //获取Activity的ContentView`
        FrameLayout mFrameLayout = activity.findViewById(android.R.id.content);

        //获取TitleBar的View实例
        View titlebar = View.inflate(activity, layoutResId, null);

        //获取Activity的Layout的根布局
        View rootView = mFrameLayout.getChildAt(0);

        //统一设置Activity的背景颜色
        mFrameLayout.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorContent));

        //此LinearLayout将替换根布局添加进ContentView中
        LinearLayout wraplayout = new LinearLayout(activity.getApplicationContext());
        wraplayout.setOrientation(LinearLayout.VERTICAL);

        //ContentView移除根布局，再将添加了标题栏和根布局的LinearLayout添加进ContentView
        mFrameLayout.removeView(rootView);

        //依次添加标题栏和根布局
        wraplayout.addView(titlebar);
        wraplayout.addView(rootView);

        mFrameLayout.addView(wraplayout);

        return titlebar;
    }
}
