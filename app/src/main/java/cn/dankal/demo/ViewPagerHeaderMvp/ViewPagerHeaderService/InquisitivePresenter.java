package cn.dankal.demo.ViewPagerHeaderMvp.ViewPagerHeaderService;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import cn.dankal.demo.ViewPagerHeaderMvp.adapter.ViewAdapterHeaderAdapter;
import java.util.ArrayList;
import java.util.List;

public class InquisitivePresenter implements InquisitiveContact.InquisitiveIPresenter {

  public InquisitiveContact.InquisitiveIView inquisitiveIView;
  public Context mContext;

  public LinearLayoutManager mLinearLayoutManager;
  public RecyclerView mRecyclerView;
  public ViewAdapterHeaderAdapter mViewAdapterHeaderAdapter;
  public List<Integer> mData;

  public InquisitivePresenter(Context context) {
    mContext = context;
  }

  @Override public void getData() {
   /* inquisitiveIView.success();
    mRecyclerView = inquisitiveIView.getRecyclerView();
    mLinearLayoutManager = inquisitiveIView.getLinearManager();
    mData = new ArrayList<>();
    for (int i = 0;i < 20 ; i++) {
        mData.add(i);
    }*/
    /*mViewAdapterHeaderAdapter = new ViewAdapterHeaderAdapter(mContext,mData);
    mRecyclerView.setAdapter(mViewAdapterHeaderAdapter);*/
  }

  @Override public void attachView(@NonNull InquisitiveContact.InquisitiveIView view) {
    inquisitiveIView = view;
  }
}
