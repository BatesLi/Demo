package cn.dankal.demo.CircleDotIndicator;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wzh.viewpager.indicator.UIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.CircleDotIndicator.adapter.CircleDotIndicatorPagerAdapter;
import cn.dankal.demo.R;

public class CircleDotIndicatorActivity extends AppCompatActivity {

    @BindView(R.id.view_page_circle_dot)
    ViewPager mViewPageCircleDot;
    @BindView(R.id.indicator_circle_dot)
    UIndicator mIndicatorCircleDot;
    List<View> mViewList;
    private CircleDotIndicatorPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_dot_indicator);
        ButterKnife.bind(this);

        mAdapter = new CircleDotIndicatorPagerAdapter(getViewList());
        mViewPageCircleDot.setAdapter(mAdapter);
        mIndicatorCircleDot.attachToViewPager(mViewPageCircleDot);
    }

    public List<View> getViewList() {
        mViewList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int ranColor = 0xff000000 | random.nextInt(0x00ffffff);
            mViewList.add(initView(ranColor));
        }
        return mViewList;
    }

    public View initView(int colcor) {
        View view = new View(this);
        ViewPager.LayoutParams params = new ViewPager.LayoutParams();
        params.height = ViewPager.LayoutParams.MATCH_PARENT;
        params.width = ViewPager.LayoutParams.MATCH_PARENT;
        view.setBackgroundColor(colcor);
        view.setLayoutParams(params);
        return view;
    }
}
