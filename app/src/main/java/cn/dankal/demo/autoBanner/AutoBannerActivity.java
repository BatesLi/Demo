package cn.dankal.demo.autoBanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yc.cn.ycbannerlib.banner.BannerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;
import cn.dankal.demo.autoBanner.adapter.ImageNormalAdapter;

public class AutoBannerActivity extends AppCompatActivity {

    @BindView(R.id.Banner_view)
    BannerView mBannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_banner);
        ButterKnife.bind(this);
        intiBannerView();
    }

    public void intiBannerView() {
        mBannerView.setPlayDelay(2000);
        mBannerView.setAdapter(new ImageNormalAdapter());
        mBannerView.setHintGravity(1);
        mBannerView.setHintMode(BannerView.HintMode.TEXT_HINT);
        boolean playing = mBannerView.isPlaying();
    }
}
