package cn.dankal.demo.customBanner.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class ImageNormalAdapter extends PagerAdapter {

    List<ImageView> mImageViewList;
    Context mContext;

    public ImageNormalAdapter(Context context, List<ImageView> imageList) {
        this.mContext = context;
        this.mImageViewList = imageList;
    }

    @Override
    public int getCount() {
        return mImageViewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return mImageViewList.get(position);
    }
}