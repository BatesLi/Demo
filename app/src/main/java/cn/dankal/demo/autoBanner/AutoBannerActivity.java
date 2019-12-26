package cn.dankal.demo.autoBanner;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.basic_lib.util.ToastUtil;
import cn.dankal.demo.R;
import cn.dankal.demo.autoBanner.adapter.AutoBannerAdapter;

public class AutoBannerActivity extends AppCompatActivity {

    //小点集合
    public List<View> mDots;
    //图片集合
    public List<ImageView> mPhotoList;
    public AutoBannerAdapter mAutoBannerAdapter;
    @BindView(R.id.view_page_auto_banner)
    ViewPager mViewPageAutoBanner;
    @BindView(R.id.linear_dot)
    LinearLayout mLinearDot;

    //图片数组
    int[] photos = {R.mipmap.item1, R.mipmap.item2, R.mipmap.item3, R.mipmap.item4, R.mipmap.item5,
            R.mipmap.item6};
    //前一个被选中的position
    private int previousPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_banner);
        ButterKnife.bind(this);
        initViewPagerData();
    }

    public void initViewPagerData() {
        //添加图片到图片list里面
        mPhotoList = new ArrayList<>();
        for (int i = 0; i < photos.length; i++) {
            ImageView imageViewOne = new ImageView(this);
            imageViewOne.setBackgroundResource(photos[i]);
            mPhotoList.add(imageViewOne);
        }
        mAutoBannerAdapter = new AutoBannerAdapter(mPhotoList, mViewPageAutoBanner);
        mViewPageAutoBanner.setAdapter(mAutoBannerAdapter);
        mViewPageAutoBanner.setCurrentItem(0);
        mViewPageAutoBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                int newPosition = position % mPhotoList.size();
                mDots.get(previousPosition).setBackgroundResource(R.drawable.ic_dot_focused);
                mDots.get(newPosition).setBackgroundResource(R.drawable.ic_dot_normal);
                previousPosition = newPosition;
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mDots = addDots(mLinearDot, ContextCompat.getDrawable(this, R.drawable.ic_dot_focused), mPhotoList.size());
        mDots.get(0).setBackgroundResource(R.drawable.ic_dot_normal);
    }
    //动态添加一个点 【确定步骤。每一个思路要用到哪些语句、方法和对象。】
    public int addDot(LinearLayout linearLayout, Drawable background) {
        View viewDot = new View(this);
        LinearLayout.LayoutParams paramsDot = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsDot.width = 26;
        paramsDot.height = 26;
        paramsDot.setMargins(4, 0, 4, 0);
        viewDot.setLayoutParams(paramsDot);
        viewDot.setBackground(background);
        //id值需要的是不固定地的值.因为dot是一个集合.
        viewDot.setId(ViewCompat.generateViewId());
        linearLayout.addView(viewDot);
        return viewDot.getId();
    }
    //添加多个轮播小点到LinearLayout
    public List<View> addDots(LinearLayout linearLayout, Drawable background, int number) {
        List<View> dots = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            int dot = addDot(linearLayout, background);
            dots.add(findViewById(dot));
        }
        return dots;
    }
}