package cn.dankal.demo.customBanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;
import cn.dankal.demo.customBanner.adapter.AbsLoopViewPageAdapter;
import cn.dankal.demo.customBanner.view.CustomBannerView;

public class CustomBannerActivity extends AppCompatActivity {

    @BindView(R.id.custom_banner_view)
    CustomBannerView mCustomBannerView;

    int[] photos = {R.mipmap.item1, R.mipmap.item2, R.mipmap.item3, R.mipmap.item4, R.mipmap.item5,
            R.mipmap.item6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_banner);
        ButterKnife.bind(this);
        initBanner();
    }

    public void initBanner() {
        //设置adapter
        mCustomBannerView.setAdapter(new ImageNormalAdapter(mCustomBannerView));
    }

    private class ImageNormalAdapter extends AbsLoopViewPageAdapter {

        public ImageNormalAdapter(CustomBannerView customBannerView) {
            super(customBannerView);
        }

        @Override
        public int getRealCound() {
            return photos.length;
        }

        @Override
        public View getView(ViewGroup container, int position) {
            return null;
        }
    }
}
