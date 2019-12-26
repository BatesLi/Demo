package cn.dankal.demo.autoBanner.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class AutoBannerAdapter extends PagerAdapter {

    public List<ImageView> mImageViews;
    public ViewPager mViewPager;

    public AutoBannerAdapter(List<ImageView> imageViews, ViewPager viewPager) {
        this.mImageViews = imageViews;
        this.mViewPager = viewPager;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mImageViews.get(position % mImageViews.size()));
        return mImageViews.get(position % mImageViews.size());
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        mViewPager.removeView(mImageViews.get(position % mImageViews.size()));
    }
}
