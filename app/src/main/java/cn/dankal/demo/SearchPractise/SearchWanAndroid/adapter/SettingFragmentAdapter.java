package cn.dankal.demo.SearchPractise.SearchWanAndroid.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.dankal.demo.R;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.fragment.CollectFragment;

public class SettingFragmentAdapter extends FragmentPagerAdapter {

  public Context mContext;
  private String[] str = {"收藏", "TODO"};
  private int[] ints = {R.mipmap.collect, R.mipmap.todo};

  public SettingFragmentAdapter(FragmentManager fm, Context context) {
    super(fm);
    this.mContext = context;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return str[position];
  }

  @Override public Fragment getItem(int position) {
    Log.e("position", position + "");
    Fragment fragment = null;
    if (position == 0) {
      fragment = new CollectFragment();
    } else {
      fragment = new CollectFragment();
    }
    return fragment;
  }

  @Override public int getCount() {
    return str.length;
  }

  public View getTabView(int position) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.user_setting_icon, null);
    ImageView imageView = view.findViewById(R.id.img_icon);
    TextView textView = view.findViewById(R.id.txt_tab);

    imageView.setBackgroundResource(ints[position]);
    textView.setText(str[position]);

    if (position == 0) {
      textView.setTextColor(imageView.getResources().getColor(R.color.black));
    }
    return view;
  }
}
