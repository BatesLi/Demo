package cn.dankal.demo.autoBanner.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yc.cn.ycbannerlib.banner.adapter.AbsStaticPagerAdapter;

import java.util.List;

import cn.dankal.demo.R;

public class ImageNormalAdapter extends AbsStaticPagerAdapter {

    public int[] imgs = {R.mipmap.item1, R.mipmap.item2, R.mipmap.item3, R.mipmap.item6};

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setImageResource(imgs[position]);
        return imageView;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }
}
