package cn.dankal.demo.Read;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;
import cn.dankal.demo.Read.adapter.HomeFragmentPageAdapter;
import cn.dankal.demo.Read.fragment.BlankFragment;
import cn.dankal.demo.Read.fragment.HomeFragment;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import java.util.ArrayList;
import java.util.List;

public class ReadActivity extends AppCompatActivity {

  @BindView(R.id.drawer_read) DrawerLayout mDrawerRead;
  @BindView(R.id.tool_bar_read) Toolbar mToolbarRead;
  @BindView(R.id.frame_layout_title_menu) FrameLayout mFrameLayoutTitleMenu;
  @BindView(R.id.img_title_menu) ImageView mImgTitleMenu;
  @BindView(R.id.radio_btn_group_control) RadioGroup mRadioBtnGroupContorl;
  @BindView(R.id.radio_btn_daily) RadioButton mRadioBtnDaily;
  @BindView(R.id.radio_btn_music) RadioButton mRadioBtnMusic;
  @BindView(R.id.radio_btn_friend) RadioButton mRadioBtnFriend;
  @BindView(R.id.view_search) MaterialSearchView mViewSearch;
  @BindView(R.id.view_pager_content) ViewPager mViewPagerContent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_read);
    ButterKnife.bind(this);
    setToolbarRead();
    initView();
  }
  public void setToolbarRead() {
    //去除toolbar的默认title
    setSupportActionBar(mToolbarRead);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
  }
  private void initView() {
    mRadioBtnGroupContorl.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override public void onCheckedChanged(RadioGroup group, int checkedId) {
      switch (checkedId) {
        case R.id.radio_btn_daily:
        mViewPagerContent.setCurrentItem(0);//设置当前页面
        break;
        case R.id.radio_btn_music:
        mViewPagerContent.setCurrentItem(1);
        break;
        case R.id.radio_btn_friend:
          mViewPagerContent.setCurrentItem(2);
          break;
      }
      }
    });
    List<Fragment> fragmentList = new ArrayList<>();
    fragmentList.add(new BlankFragment());
    fragmentList.add(new HomeFragment());
    fragmentList.add(new BlankFragment());

    mViewPagerContent.setAdapter(new HomeFragmentPageAdapter(getSupportFragmentManager(),fragmentList));
    mViewPagerContent.setCurrentItem(1);
    mViewPagerContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }
    //onPageSelected(int position)：这个方法有一个参数position，代表哪个页面被选中。
      @Override public void onPageSelected(int position) {
        switch (position) {
          case 0:
            mRadioBtnGroupContorl.check(R.id.radio_btn_daily);
            break;
          case 1:
            mRadioBtnGroupContorl.check(R.id.radio_btn_music);
            break;
          case 2:
            mRadioBtnGroupContorl.check(R.id.radio_btn_friend);
            break;
        }
      }

      @Override public void onPageScrollStateChanged(int state) {

      }
    });
  }

}

