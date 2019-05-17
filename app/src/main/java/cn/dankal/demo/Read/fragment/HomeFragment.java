package cn.dankal.demo.Read.fragment;


import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import butterknife.BindView;
import cn.dankal.demo.R;
import cn.dankal.demo.Read.adapter.HomeFragmentPageAdapter;
import cn.dankal.demo.Read.app.AppConstants;
import cn.dankal.demo.Read.base.BaseFragment;
import java.util.ArrayList;

public class HomeFragment extends BaseFragment {

  @NonNull
  @BindView(R.id.tab_gank) TabLayout mTabGank;
  @BindView(R.id.view_pager_gank) ViewPager mViewPagerGank;

  private ArrayList<String> mTitleList = new ArrayList<>(4);
  private ArrayList<Fragment> mFragments = new ArrayList<>(4);
  private HomeFragmentPageAdapter homeFragmentPageAdapter;

  @Override protected int getLayoutId() {
    return R.layout.fragment_gank;
  }

  @Override protected void initView() {
    initGankFragmentList();
    homeFragmentPageAdapter = new HomeFragmentPageAdapter(getChildFragmentManager()
        ,mFragments,mTitleList);
    mViewPagerGank.setAdapter(homeFragmentPageAdapter);
    homeFragmentPageAdapter.notifyDataSetChanged();
    mTabGank.setTabMode(TabLayout.MODE_FIXED);
    mTabGank.setupWithViewPager(mViewPagerGank);
  }

  @Override protected void loadData() {
    setState(AppConstants.STATE_SUCCESS);
  }

  @Override protected void initInject() {

  }
  private void initGankFragmentList() {
    if (mTitleList.size() != 0) {
        return;
    }
    mTitleList.add("知乎日报");
    mTitleList.add("头条新闻");
    mTitleList.add("排行榜");
    mTitleList.add("最新电影");
    mFragments.add(new BlankFragment());
    mFragments.add(new TopNewsFragment());
    mFragments.add(new BlankFragment());
    mFragments.add(new BlankFragment());
  }
}
