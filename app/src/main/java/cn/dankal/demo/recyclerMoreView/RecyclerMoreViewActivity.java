package cn.dankal.demo.recyclerMoreView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;
import java.util.ArrayList;
import java.util.List;

import static cn.dankal.demo.recyclerMoreView.RecyclerMoreAdapter.ITEM_ONE;
import static cn.dankal.demo.recyclerMoreView.RecyclerMoreAdapter.ITEM_THREE;
import static cn.dankal.demo.recyclerMoreView.RecyclerMoreAdapter.ITEM_TWO;

public class RecyclerMoreViewActivity extends AppCompatActivity {

  @BindView(R.id.toolbar_recycler_more) Toolbar mToolbarRecyclerMore;
  @BindView(R.id.recycler_more) RecyclerView mRecyclerMore;

  public List<MoreData> mMoreDataList;
  public RecyclerMoreAdapter mRecyclerMoreAdapter;
  public MoreData moreData;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recycler_more_view);
    ButterKnife.bind(this);

    setToolbar();
    initRecyclerData();
    mRecyclerMore.setLayoutManager(new LinearLayoutManager(this));
  }

  public void setToolbar() {
    setSupportActionBar(mToolbarRecyclerMore);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    mToolbarRecyclerMore.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        finish();
      }
    });
  }

  protected void initRecyclerData() {
    mMoreDataList = new ArrayList<>();
    //只是显示一个item的Type，其余的都不显示 why
     /*moreData = new MoreData();
       if (moreData.getViewType() == 0) {
           moreData.setViewType(ITEM_ONE);
           mMoreDataList.add(moreData);
       }else if (moreData.getViewType() == 1) {
           moreData.setViewType(ITEM_TWO);
           mMoreDataList.add(moreData);
       }else {
          moreData.setTitle("列表");
          mMoreDataList.add(moreData);
       }*/
    for (int i = 0; i < 10; i++) {
      if (i == 0) {
        MoreData moreData = new MoreData();
        moreData.setViewType(ITEM_ONE);
        mMoreDataList.add(moreData);
      } else if (i == 1) {
        MoreData moreDataTwo = new MoreData();
        moreDataTwo.setViewType(ITEM_TWO);
        mMoreDataList.add(moreDataTwo);
      } else {
        MoreData moreDataThree = new MoreData();
        moreDataThree.setViewType(ITEM_THREE);
        moreDataThree.setTitle("列表");
        mMoreDataList.add(moreDataThree);
      }
    }
    mRecyclerMoreAdapter = new RecyclerMoreAdapter(this, mMoreDataList);
    mRecyclerMore.setAdapter(mRecyclerMoreAdapter);
  }
}
