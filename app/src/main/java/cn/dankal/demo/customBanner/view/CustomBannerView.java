package cn.dankal.demo.customBanner.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import cn.dankal.basic_lib.util.ToastUtil;
import cn.dankal.demo.R;

public class CustomBannerView extends RelativeLayout {

    private int mBackgoundColor;

    public CustomBannerViewPage mCustomBannerViewPage = new CustomBannerViewPage(getContext());

    public CustomBannerView(Context context) {
        super(context);
    }

    public CustomBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomBannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attributeSet) {

       /* TypedArray type = getContext().obtainStyledAttributes(attributeSet,R.styleable.customBannerViewStyleable);
        mBackgoundColor  = type.getColor(R.styleable.customBannerViewStyleable_background_Custom, getResources()
        .getColor(R.color.black));
        type.recycle();*/

        ToastUtil.toToast("数字: " + mCustomBannerViewPage);
        mCustomBannerViewPage.setId(R.id.view_page_banner);
        mCustomBannerViewPage.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(mCustomBannerViewPage);
    }

    //AbsLoopViewPageAdapter 继承自PagerAdapter
    //ImageNormalAdapter 继承自 AbsLoopViewPageAdapter
    public void setAdapter(PagerAdapter pagerAdapter) {
        mCustomBannerViewPage.setAdapter(pagerAdapter);
    }
}
