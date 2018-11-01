package cn.dankal.demo.ViewPagerHeaderMvp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.basic_lib.util.ToastUtil;
import cn.dankal.demo.R;
import cn.dankal.demo.ViewPagerHeaderMvp.ViewPagerHeaderService.InquisitiveContact;
import cn.dankal.demo.ViewPagerHeaderMvp.ViewPagerHeaderService.InquisitivePresenter;
import cn.dankal.demo.ViewPagerHeaderMvp.adapter.ViewAdapterHeaderAdapter;
import java.util.ArrayList;
import java.util.List;

public class InquisitiveFragment extends Fragment implements InquisitiveContact.InquisitiveIView {

  public LinearLayoutManager mLinearLayoutManager;
  public InquisitivePresenter mInquisitivePresenter;

  @BindView(R.id.Recycler_fragment_inquisitive) RecyclerView mRecyclerFragmentInquisitive;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container
      , Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_inquisitive, container, false);
    ButterKnife.bind(this, view);

    mInquisitivePresenter = new InquisitivePresenter(getContext());
    mInquisitivePresenter.getData();
    mInquisitivePresenter.attachView(this);
    return view;
  }

  @Override public RecyclerView getRecyclerView() {
    return mRecyclerFragmentInquisitive;
  }

  @Override public LinearLayoutManager getLinearManager() {
    mLinearLayoutManager = new LinearLayoutManager(getContext());
    mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    mRecyclerFragmentInquisitive.setLayoutManager(mLinearLayoutManager);
    return mLinearLayoutManager;
  }

  @Override public void isRefresh(boolean fresh) {

  }

  @Override public void loadError() {

  }

  @Override public void success() {
    ToastUtil.toToast("显示数据");
  }
}















