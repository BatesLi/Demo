package cn.dankal.demo.customBanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;
import cn.dankal.demo.customBanner.view.CustomBannerView;

public class CustomBannerActivity extends AppCompatActivity {

    @BindView(R.id.custom_banner_view)
    CustomBannerView mCustomBannerView;
    @BindView(R.id.view_custom_banner_dot)
    View mViewCustomBannerDot;
    @BindView(R.id.linear_custom_banner_list_dot)
    LinearLayout mLinearCustomBannerListDot;

    private List<Integer> mDotList;
    private LinearLayout.LayoutParams mLayoutDotParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_banner);
        ButterKnife.bind(this);
        initBanner();
        initDot();
    }

    public void initBanner() {
        mCustomBannerView.setOnCustomBannerListener(new CustomBannerView.OnCustomBannerListener() {
            @Override
            public void OnPageScrollListener(float positionOffset, int positionOffsetPixels) {
                float leftMargin = positionOffset * 30;
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mViewCustomBannerDot.getLayoutParams();
                params.leftMargin = (int) leftMargin;
                mViewCustomBannerDot.setLayoutParams(params);
            }

            @Override
            public void OnPageScrollListener(int position) {

            }
        });
    }

    public void initDot() {
        mDotList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mDotList.add(i);
        }
        for (int i = 0; i < mDotList.size(); i++) {
            View viewDot = new View(this);
            viewDot.setBackgroundResource(R.drawable.custom_banner_select_dot);
            mLayoutDotParams = new LinearLayout.LayoutParams(20, 20);
            if (i != 0) {
                mLayoutDotParams.leftMargin = 10;
            }
            viewDot.setLayoutParams(mLayoutDotParams);
            mLinearCustomBannerListDot.addView(viewDot);
        }
    }
}
