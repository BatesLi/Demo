package cn.dankal.demo.Read.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.dankal.demo.R;
import cn.dankal.demo.Read.viewHelp.LoadingPage;

public class TestFragment extends Fragment {

  public LoadingPage mLoadingPage;

  @Override
  public View onCreateView(@Nullable LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
    mLoadingPage = new LoadingPage(getContext()) {
      @Override protected int getLayoutId() {
        return R.layout.item_news;
      }

      @Override protected void initView() {

      }

      @Override protected void loadData() {

      }
    };
    return mLoadingPage.contentView;
  }
}
