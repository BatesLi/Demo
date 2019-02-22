package cn.dankal.demo.smallPhotoAlbum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;

public class SmallAlbumActivity extends AppCompatActivity {

  @BindView(R.id.toolbar_album) Toolbar mToolbarAlbum;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_small_album);
    ButterKnife.bind(this);
    setToolbar();
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
}
