package cn.dankal.demo.SearchPractise.SearchWanAndroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.Api.Contact;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.Api.WebTask;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.adapter.UserPageFragmentAdapter;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.base.BaseActivity;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.custom.ClearEditText;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.fragment.HomeFragment;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.fragment.SettingFragment;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.model.SearchResult;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.presenter.SearchPresenter;
import cn.dankal.demo.SearchPractise.flowlayout.FlowLayout;
import cn.dankal.demo.SearchPractise.flowlayout.TagAdapter;
import cn.dankal.demo.SearchPractise.flowlayout.TagFlowLayout;
import com.bottom.NavigationController;
import com.bottom.PageNavigationView;
import com.bottom.item.BaseTabItem;
import com.bottom.item.NormalItemView;
import com.gyf.immersionbar.ImmersionBar;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BatesLi
 * @date
 * @org
 * @email
 * @describe 玩Android的search模块实现类
 */

public class WanAndroidActivity extends BaseActivity implements Contact.SearchView {

  public Contact.SearchPresenter mPresenter;
  @BindView(R.id.edit_wan_search) ClearEditText editWanSearch;
  @BindView(R.id.img_btn_wan_search) ImageButton imgBtnWanSearch;
  @BindView(R.id.flow_layout_wan_search) TagFlowLayout flowLayoutWanSearch;
  public List<Fragment> mFragmentList;
  @BindView(R.id.view_page_wan_search) ViewPager mViewPagerWanSearch;
  @BindView(R.id.page_navigation_wan_search) PageNavigationView mPageNavigationWanSearch;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_wan_andorid_search);
    ButterKnife.bind(this);
    initFragment();

    ImmersionBar.with(this).statusBarColor(R.color.bg_daily_mode).autoDarkModeEnable(true)
        .fitsSystemWindows(true).keyboardEnable(true).init();
    if (mPresenter == null) {
      mPresenter = SearchPresenter.createPresenter(this, WebTask.getInstance());
    }
    mPresenter.getHotKey();//数据

    imgBtnWanSearch.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (!TextUtils.isEmpty(editWanSearch.getText().toString())) {
          Intent intent = new Intent(v.getContext(), SearchResultActivity.class);
          intent.putExtra("HOT_KEY", editWanSearch.getText().toString());
          startActivity(intent);
          finish();
        }
      }
    });
  }

  @Override public void setHotKey(String[] keys) {
    final LayoutInflater layoutInflater = LayoutInflater.from(this);
    flowLayoutWanSearch.setAdapter(new TagAdapter<String>(keys) {
      @Override public View getView(FlowLayout parent, int position, String s) {
        TextView tv = (TextView) layoutInflater.inflate(R.layout.item_flow_txt,
            flowLayoutWanSearch, false);
        tv.setText(s);
        return tv;
      }
    });
    flowLayoutWanSearch.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
      @Override public void onTagClick(View view, int position, FlowLayout parent) {
        if (TextUtils.isEmpty(editWanSearch.getText().toString())) {
          editWanSearch.setText(keys[position]);
        } else {
          editWanSearch.setText(editWanSearch.getText().append("" + keys[position]));
        }
      }
    });
  }

  @Override public void setPresenter(Contact.SearchPresenter presenter) {
    this.mPresenter = presenter;
  }

  @Override public void setHotKeyContent(int flag, List<SearchResult.DataBean.Datas> data) {

  }

  @Override public void collectArticle(int position, boolean isCollect) {

  }

  @Override public void goWedActivity(String URL) {

  }

  @Override public void initView(List data) {

  }

  public void initFragment() {
    mFragmentList = new ArrayList<>();
    mFragmentList.add(new HomeFragment());
    mFragmentList.add(new HomeFragment());
    mFragmentList.add(new HomeFragment());
    mFragmentList.add(new SettingFragment());

    mViewPagerWanSearch.setAdapter(new UserPageFragmentAdapter(getSupportFragmentManager(),
        mFragmentList, this));

    NavigationController navigationController = mPageNavigationWanSearch.custom()
        .addItem(newItem(R.mipmap.home_no, R.mipmap.home, ""))
        .addItem(newItem(R.mipmap.tree_no, R.mipmap.tree, ""))
        .addItem(newItem(R.mipmap.project_no, R.mipmap.project_yes, ""))
        .addItem(newItem(R.mipmap.user_no, R.mipmap.user, ""))
        .build();

    navigationController.setupWithViewPager(mViewPagerWanSearch);
  }

  private BaseTabItem newItem(int drawableRes, int checkedDrawableRes, String text) {
    NormalItemView normalItemView = new NormalItemView(this);
    normalItemView.initialize(drawableRes, checkedDrawableRes, text);
    normalItemView.setTextDefaultColor(Color.GRAY);
    normalItemView.setTextCheckedColor(0xFF009688);
    return normalItemView;
  }
}
