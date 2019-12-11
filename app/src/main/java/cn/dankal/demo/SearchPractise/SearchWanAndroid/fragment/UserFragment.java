package cn.dankal.demo.SearchPractise.SearchWanAndroid.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.dankal.demo.R;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.Utils.IndicatorLineUtil;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.adapter.SettingFragmentAdapter;

public class UserFragment extends Fragment {

  public SettingFragmentAdapter mSettingFragmentAdapter;
  @BindView(R.id.txt_user_name) TextView mTxtUserName;
  @BindView(R.id.txt_user_id) TextView MTxtUserId;
  @BindView(R.id.txt_user_id_show) TextView mTxtUserIdShow;
  @BindView(R.id.tab_user) TabLayout mTabUser;
  @BindView(R.id.view_page_user) ViewPager mViewPageUser;
  @BindView(R.id.linear_layout_show) LinearLayout mLinearLayoutShow;
  Unbinder unbinder;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_user_setting, container, false);
    unbinder = ButterKnife.bind(this, view);
    initData();
      initView();
      mTabUser.post(new Runnable() {
          @Override
          public void run() {
              IndicatorLineUtil.setIndicator(mTabUser, 30, 30);
          }
      });
    return view;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

    private void initView() {
    for (int i = 0; i < mTabUser.getTabCount(); i++) {
      mTabUser.getTabAt(i).setCustomView(mSettingFragmentAdapter.getTabView(i));
    }
        mTabUser.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //实现Tab标签被选中的事务状态
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tab.getCustomView().findViewById(R.id.img_icon).setBackgroundResource(R.mipmap.collect);
                        break;
                    case 1:
                        tab.getCustomView().findViewById(R.id.img_icon).setBackgroundResource(R.mipmap.todo);
                        break;
                    default:
                        break;
                }
                TextView textView = tab.getCustomView().findViewById(R.id.txt_tab);
                textView.setTextColor(getResources().getColor(R.color.colorAccent));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView textView = tab.getCustomView().findViewById(R.id.txt_tab);
                textView.setTextColor(getResources().getColor(R.color.black));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public void initData() {
        mSettingFragmentAdapter =
                new SettingFragmentAdapter(getChildFragmentManager(), this.getActivity());
        mViewPageUser.setAdapter(mSettingFragmentAdapter);
        mTabUser.setupWithViewPager(mViewPageUser);
    }

}

