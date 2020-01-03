package cn.dankal.demo.customBanner.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class CustomBannerViewPage extends ViewPager {


    public CustomBannerViewPage(@NonNull Context context) {
        super(context);
    }

    public CustomBannerViewPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
