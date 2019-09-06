package cn.dankal.demo.SearchPractise.SearchWanAndroid.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

public class UserPageFragmentAdapter extends FragmentPagerAdapter {

  public List<Fragment> mFragmentList;
  public Context mContext;

  public UserPageFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList, Context context) {
    super(fm);
    this.mFragmentList = fragmentList;
    this.mContext = context;
  }

  @Override public Fragment getItem(int position) {
    return mFragmentList.get(position);
  }

  @Override public int getCount() {
    return mFragmentList.size();
  }
}
