package cn.dankal.demo.CircleDotIndicator.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class CircleDotIndicatorPagerAdapter extends PagerAdapter {

    public List<View> mViews;

    public CircleDotIndicatorPagerAdapter(List<View> viewList) {
        this.mViews = viewList;
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int postion) {
        container.addView(mViews.get(postion));
        return mViews.get(postion);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }
}


