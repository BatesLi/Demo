package cn.dankal.demo.ViewPagerHeaderMvp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import cn.dankal.demo.ViewPagerHeaderMvp.base.BaseFragment;
import java.util.List;

public class ViewPagerFgAdapter extends FragmentPagerAdapter {

  private String tag;
  private List<Fragment> baseFragmentList;

  public ViewPagerFgAdapter(FragmentManager fragmentManager, List<Fragment> baseFragmentList
      , String tag) {
    super(fragmentManager);
    this.baseFragmentList = baseFragmentList;
    this.tag = tag;
  }

  @Override public Fragment getItem(int position) {
    return baseFragmentList.get(position);
  }

  @Override public int getCount() {
    if (baseFragmentList != null) {
      return baseFragmentList.size();
    }
    return 0;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    if (tag.equals("main_view_pager")) {
      switch (position) {
        case 0:
          return "知乎";
        case 1:
          return "干货";
        case 2:
          return "满足你的好奇心";
      }
    }
    return null;
  }
}
