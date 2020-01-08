package cn.dankal.demo.customBanner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;
import cn.dankal.demo.customBanner.adapter.ImageNormalAdapter;
import cn.dankal.demo.customBanner.view.CustomBannerView;

public class CustomBannerActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.custom_banner_view)
    CustomBannerView mCustomBannerView;
    private ImageNormalAdapter mNormalAdapter;

    List<ImageView> mImageViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_banner);
        ButterKnife.bind(this);
        initBanner();
    }

    public void initBanner() {
        //设置adapter
    }
}
