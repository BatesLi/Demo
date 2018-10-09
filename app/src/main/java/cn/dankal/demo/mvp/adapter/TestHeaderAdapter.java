package cn.dankal.demo.mvp.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.basic_lib.util.ToastUtil;
import cn.dankal.demo.R;
import cn.dankal.demo.mvp.ViewHolder.BottomViewHolder;
import cn.dankal.demo.mvp.ViewHolder.ContentViewHolder;
import cn.dankal.demo.mvp.ViewHolder.HeaderViewHolder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TestHeaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int ITEM_TYPE_HEADER = 0;
  private static final int ITEM_TYPE_CONTENT = 1;
  private static final int ITEM_TYPE_BOTTOM = 2;

  //Handler 用到的参数值
  private static final int UPTATE_VIEWPAGER = 0;

  public Context mContext;
  public List<Integer> mData;
  public List<ImageView> mHeaderAdData;
  public int[] imgData =
      {R.mipmap.item1, R.mipmap.item2, R.mipmap.item3, R.mipmap.item4, R.mipmap.item5};
  public LayoutInflater mLayoutInflater;
  public ViewPagerImageAdapterHead mViewPagerImageAdapterHead;

  public int mHeaderView = 0;//头部
  public int mBottomView = 1;

  //设置当前 第几个图片 被选中
  private int currentIndex = 0;
  private ImageView[] mCircleImages;//底部只是当前页面的小圆点

  public TestHeaderAdapter(Context context, List<Integer> data) {
    this.mContext = context;
    this.mData = data;
    mLayoutInflater = LayoutInflater.from(context);
  }

  //我需要确定header在0的位置 / getItemViewType()通过这个方法对不同位置的数据进行处理
  @Override
  public int getItemViewType(int position) {
    if (mHeaderView == 0 && position == 0) {
      return ITEM_TYPE_HEADER;
    } else if (mBottomView != 0 && position >= mData.size() + mHeaderView) {
      return ITEM_TYPE_BOTTOM;
    } else {
      return ITEM_TYPE_CONTENT;
    }
  }

  @NonNull @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if (viewType == ITEM_TYPE_HEADER) {
      return new HeaderViewHolder(
          mLayoutInflater.inflate(R.layout.item_news_header, null));
    } else if (viewType == ITEM_TYPE_CONTENT) {
      return new ContentViewHolder(mLayoutInflater.inflate(R.layout.item_news, parent, false));
    } else if (viewType == ITEM_TYPE_BOTTOM) {
      return new BottomViewHolder(
          mLayoutInflater.inflate(R.layout.item_news_bottom, parent, false));
    }
    return null;
  }

  //onBindViewHolder()此方法是绑定数据
  @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof ContentViewHolder) {
      //转型
      //ContentViewHolder contentHolder = (ContentViewHolder) holder;
    } else if (holder instanceof HeaderViewHolder) {
      HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

      mHeaderAdData = new ArrayList<>();
      for (int i = 0; i <= 4; i++) {
        ImageView imageView = new ImageView(mContext);
        imageView.setBackgroundResource(imgData[i]);
        mHeaderAdData.add(imageView);
      }
      mViewPagerImageAdapterHead = new ViewPagerImageAdapterHead(mContext, mHeaderAdData);
      setUpViewPager(headerHolder.mVpHeader, mHeaderAdData, headerHolder.mLlHottestIndicator);
    }
  }

  //setUpViewPager()还能里面的参数还能设置轮播图片下面的小圆点
  private void setUpViewPager(final ViewPager viewPager, List<ImageView> imageVpData,
      LinearLayout llBottom) {
    mViewPagerImageAdapterHead = new ViewPagerImageAdapterHead(mContext, imageVpData);
    viewPager.setAdapter(mViewPagerImageAdapterHead);

    final Handler handler = new Handler() {
      public void handleMessage(Message message) {
        switch (message.what) {
          case UPTATE_VIEWPAGER:
            if (message.arg1 != 0) {
              //setCurrentItem(int index)方法主要用来制定初始化的页面。
              // 例如加入3个页面通过setCurrentItem(0)制定第一个页面为当前页面
              viewPager.setCurrentItem(message.arg1);
            } else {
              //false 当从末页调到首页是，不显示翻页动画效果
              viewPager.setCurrentItem(message.arg1, false);
            }
            break;
        }
      }
    };

    //创建底部指示位置的导航栏

    //创建一个ImageView类型的数组,仅仅只有数量，数组里面并没有具体的元素
    mCircleImages = new ImageView[mHeaderAdData.size()];
    for (int i = 0; i < mCircleImages.length; i++) {
      ImageView imageView = new ImageView(mContext);
      imageView.setBackgroundResource(R.drawable.indicator_select);
      mCircleImages[i] = imageView;
      if (mCircleImages[i] != null) {
        llBottom.addView(mCircleImages[i]);
      } else {
        ToastUtil.toToast("数据为空");
      }
    }
    //设置viewpager的滑动事件
    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override public void onPageSelected(int position) {
        int total = mCircleImages.length;
        for (int i = 0; i < total; i++) {
          if (i == position) {
            mCircleImages[i].setBackgroundResource(R.drawable.indicator_select);
          } else {
            mCircleImages[i].setBackgroundResource(R.drawable.indicator_not_select);
          }
        }
        currentIndex = position;
      }

      @Override public void onPageScrollStateChanged(int state) {
        //header广告轮播定时器
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
          @Override public void run() {

          }
        }, 6000, 6000);
      }
    });
  }

  @Override public int getItemCount() {
    return mHeaderView + mData.size() + mBottomView;
  }
}
