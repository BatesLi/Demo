package cn.dankal.demo.customBanner.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.dankal.demo.customBanner.view.CustomBannerView;

public abstract class AbsLoopViewPageAdapter extends PagerAdapter {

    public List<View> mViewList = new ArrayList<>();
    public CustomBannerView mViewPage;

    public AbsLoopViewPageAdapter(CustomBannerView customBannerView) {
        this.mViewPage = customBannerView;
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    //获取轮播图片数量
    public abstract int getRealCound();

    //创建view
    public abstract View getView(ViewGroup container, int position);
}
