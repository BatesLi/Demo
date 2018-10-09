package cn.dankal.demo.mvp.fragment;

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
import cn.dankal.demo.R;
import cn.dankal.demo.mvp.adapter.TestHeaderAdapter;
import java.util.ArrayList;
import java.util.List;

public class InquisitiveFragment extends Fragment {

  public List<Integer> mData;
  public TestHeaderAdapter mTestHeaderAdapter;
  public LinearLayoutManager mLinearLayoutManager;
  @BindView(R.id.Recycler_fragment_inquisitive) RecyclerView mRecyclerFragmentInquisitive;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container
      , Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_inquisitive, container, false);
    ButterKnife.bind(this, view);
    initData();
    initView();
    return view;
  }

  private void initData() {
    mData = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      mData.add(i);
    }
  }

  private void initView() {
    mTestHeaderAdapter = new TestHeaderAdapter(getContext(), mData);
    mLinearLayoutManager = new LinearLayoutManager(getContext());
    mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    mRecyclerFragmentInquisitive.setLayoutManager(mLinearLayoutManager);
    mRecyclerFragmentInquisitive.setAdapter(mTestHeaderAdapter);
  }
}















