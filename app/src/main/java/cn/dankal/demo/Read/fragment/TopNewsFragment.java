package cn.dankal.demo.Read.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import butterknife.BindView;
import cn.dankal.demo.R;
import cn.dankal.demo.Read.adapter.TopNewsAdapter;
import cn.dankal.demo.Read.base.BaseFragment;
import cn.dankal.demo.Read.bean.NewsListBean;
import cn.dankal.demo.Read.contact.TopNewsPresenter;
import cn.dankal.demo.Read.inject.component.fragment.DaggerTopNewsComponent;
import cn.dankal.demo.Read.inject.module.TopNewsHttpModule;
import cn.dankal.demo.Read.inject.module.TopNewsModule;
import cn.dankal.demo.Read.presenter.TopNewsPresenterImpl;
import cn.dankal.demo.Read.viewHelp.EasyLoadMoreView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import java.util.ArrayList;

public class TopNewsFragment extends BaseFragment<TopNewsPresenterImpl>
implements TopNewsPresenter.View, BaseQuickAdapter.RequestLoadMoreListener {

  @BindView(R.id.recycler_activity) RecyclerView mRecyclerActivity;

  private ArrayList<NewsListBean.NewsBean> newsList;
  private EasyLoadMoreView mEasyLoadMoreView;

  private int currentIndex = 0;
  private int index;

  @Override protected int getLayoutId() {
    return R.layout.activity_recyclerview;
  }

  @Override protected void initView() {
    mAdapter.setLoadMoreView(mEasyLoadMoreView);
    mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
    mAdapter.setOnLoadMoreListener(this,mRecyclerActivity);
    mRecyclerActivity.setLayoutManager(new StaggeredGridLayoutManager(2
        , StaggeredGridLayoutManager.VERTICAL));
    mRecyclerActivity.setAdapter(mAdapter);
    ((TopNewsAdapter)mAdapter).setOnItemClickListener(new TopNewsAdapter.OnItemClickListener() {
      @Override public void onItemClickListener(String id, String imgUrl, View view) {
        startZhiHuDetailActivity();
      }
    });
  }

  @Override protected void loadData() {
    currentIndex = 0;
    mPresenter.fetchTopNewsList(currentIndex);//mPresenter为null,数据在哪儿
  }
  /*此方法*//*dagger2注入*//*，暂时不懂
  但是mPresenter不为null的原因在这里，数据也从这里来*/
  @Override protected void initInject() {
    DaggerTopNewsComponent.builder()
        .topNewsHttpModule(new TopNewsHttpModule())
        .topNewsModule(new TopNewsModule())
        .build().injectTopNews(this);
  }
  //根据需求后期再写
  @Override public void refreshView(NewsListBean mData) {
   /* LogUtils.e("aaaacurrentIndex"+currentIndex);
    newsList = mData.getNewsList();
    mAdapter.addData(newsList);
    index+=1;
    currentIndex = mAdapter.getData().size()-2*index;
    mAdapter.loadMoreComplete();*/
  }
  //数据在这里（不对，mPresenter依旧为null）
  @Override public void onLoadMoreRequested() {
    if (currentIndex >= 60) {
        mAdapter.loadMoreEnd();
    }else {
        mPresenter.fetchTopNewsList(currentIndex);
    }
  }
  //item跳转指向的activity
  private void startZhiHuDetailActivity() {

  }
}
