package cn.dankal.demo.ViewPagerHeaderMvp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
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

  /*系统创建 Fragment 的时候回调，介于 onAttach() 和 onCreateView() 之间
   * 一般用于初始化一些数据
   * 值得注意的是，此时 Activity 还在创建中，因此不能在执行一些跟 Activity UI 相关的操作
   * 否则，会出现一些难以预料的问题，比如：NullPointException
   * 如果要对 Activity 上的 UI 进行操作，建议在 onActivityCreated() 中操作*/
  @Override
  public void onCreate(Bundle savedInstance) {
    super.onCreate(savedInstance);

    mInquisitivePresenter = new InquisitivePresenter(getContext());
    mInquisitivePresenter.getData();
    mInquisitivePresenter.attachView(this);
  }

  /*此处应该只进行布局的初始化，而不应该执行耗时操作，如网络请求、数据库读取，应该进行View相关的操作*/
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container
      , Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_inquisitive, container, false);
    ButterKnife.bind(this, view);
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
    // ToastUtil.toToast("显示数据");
    Toast.makeText(getContext(), "显示数据", Toast.LENGTH_LONG).show();
  }
}















