package cn.dankal.demo.autoBanner;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.didichuxing.doraemonkit.DoraemonKit;

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

        mViewPageAutoBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ToastUtil.toToast("图片位置" + position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    //动态添加一个点 【确定步骤。每一个思路要用到哪些语句、方法和对象。】
   /* public int addDot(int dot) {
        View viewDot = new View(this);
        return viewDot.getId();
    }
   //添加多个轮播小点到LinearLayout
    public List<View> addDots() {
        List<View> viewList = new ArrayList<>();
        for (int i = 0; i < mPhotoList.size(); i++) {
            //mLinearDot.addView(viewList.get(addDot(i)));
        }
        return viewList;
    }*/
}


