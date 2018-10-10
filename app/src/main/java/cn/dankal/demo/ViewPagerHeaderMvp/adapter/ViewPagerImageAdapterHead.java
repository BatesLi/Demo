package cn.dankal.demo.ViewPagerHeaderMvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.List;

public class ViewPagerImageAdapterHead extends PagerAdapter {

  public List<ImageView> mHeadImageList;
  public Context mContext;

  public ViewPagerImageAdapterHead(Context mContext, List<ImageView> headImageList) {
    this.mContext = mContext;
    this.mHeadImageList = headImageList;
  }

  @Override public int getCount() {
    //同样是使用ViewPager,但是getCount返回值不用为Integer.MAX_VALUE，只需返回图片数量+2
    return Integer.MAX_VALUE;
  }

  @Override public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
    return view == object;
  }

  @Override
  public ImageView instantiateItem(ViewGroup container, int position) {
    /**
     *    这个position是当前页面的下一个页面的缓存，若是想做滑动，position=当前item-1反之则加一
     *    举个例子这个viewpager总共包含了imagesize+2=7个页面
     *    positio=5时 position % ImageSize=0，切换到了第一个position
     */
    ImageView imageView = mHeadImageList.get(position % mHeadImageList.size());
    container.addView(imageView);
    return imageView;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    ImageView imageView = (ImageView) object;
    container.removeView(imageView);
  }

  @Override
  public void finishUpdate(ViewGroup container) {

  }
}






