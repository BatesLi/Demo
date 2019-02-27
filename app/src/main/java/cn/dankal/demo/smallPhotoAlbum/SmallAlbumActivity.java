package cn.dankal.demo.smallPhotoAlbum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.basic_lib.util.ToastUtil;
import cn.dankal.demo.R;

public class SmallAlbumActivity extends AppCompatActivity {

  @BindView(R.id.toolbar_album) Toolbar mToolbarAlbum;
  @BindView(R.id.img_photo) ImageView mImgPhoto;
  @BindView(R.id.txt_photo_information) TextView mTxtPhotoInformation;
  @BindView(R.id.btn_previous_photo) Button mBtnPreviousPhoto;
  @BindView(R.id.btn_next_photo) Button mBtnNextPhoto;

  int[] imageViews = {R.drawable.leaf_autumn, R.drawable.leaf_drop, R.drawable.leaf_red
      , R.drawable.leaf_wind};

  String[] photoInformation = {"第一张", "第二张", "第三张", "第四张"};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_small_album);
    ButterKnife.bind(this);
    setToolbar();
    setPhotoBtn();
  }

  public void setToolbar() {
    setSupportActionBar(mToolbarAlbum);
    getSupportActionBar().setDisplayShowTitleEnabled(false); //toolbar去除默认title显示
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    mToolbarAlbum.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        finish();
      }
    });
  }

  public void setPhotoBtn() {
    mBtnNextPhoto.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        for (int i = 0; i < photoInformation.length; i++) {
          mTxtPhotoInformation.setText(photoInformation[i]);
        }
        ToastUtil.toToast("下一张");
      }
    });
  }
}
