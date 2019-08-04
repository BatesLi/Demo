package cn.dankal.demo.SearchPractise.SearchWanAndroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.basic_lib.util.ToastUtil;
import cn.dankal.demo.R;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.Api.Contact;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.Api.WebTask;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.Utils.StringUtils;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.adapter.SearchResultAdapter;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.base.BaseActivity;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.model.SearchResult;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.presenter.SearchPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BatesLi
 * @date
 * @org
 * @email
 * @describe
 */

public class SearchResultActivity extends BaseActivity implements Contact.SearchView,
    OnTitleBarListener {

  public SearchResultAdapter mSearchResultAdapter;
  @BindView(R.id.search_result_title) TitleBar searchResultTitle;
  @BindView(R.id.txt_search_result) TextView txtSearchResult;
  @BindView(R.id.smart_search_result_refresh_content) SmartRefreshLayout
      smartSearchResultRefreshContent;
  @BindView(R.id.recycler_result) RecyclerView mRecyclerResult;
  private int page = 1;
  private Contact.SearchPresenter mSearchPresenter;
  private List<SearchResult.DataBean.Datas> mList = new ArrayList<>();

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search_result);
    ButterKnife.bind(this);

    Intent intent = getIntent();
    final String hotkey = intent.getStringExtra("HOT_KEY");
    txtSearchResult.setText(hotkey);
    mRecyclerResult.setLayoutManager(new LinearLayoutManager(this));
    smartSearchResultRefreshContent.setEnableRefresh(true);

    if (mSearchPresenter == null) {
      mSearchPresenter = new SearchPresenter(this, WebTask.getInstance());
    }
    searchResultTitle.setOnTitleBarListener(this);
    mSearchPresenter.initView();
    mSearchPresenter.getHotKeyContent(hotkey, page);

    smartSearchResultRefreshContent.setOnRefreshListener(new OnRefreshListener() {
      @Override public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page = 0;
        mSearchPresenter.getHotKeyContent(hotkey, page);
        smartSearchResultRefreshContent.finishRefresh();
      }
    });
    smartSearchResultRefreshContent.setOnLoadMoreListener(new OnLoadMoreListener() {
      @Override public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page = page + 1;
        ToastUtil.toToast("page的数值变化" + page);
        mSearchPresenter.addHotKeyContent(hotkey, page);
        smartSearchResultRefreshContent.finishLoadMore(2000);
      }
    });
  }

  @Override public void initView(List<SearchResult.DataBean.Datas> data) {
    mSearchResultAdapter = new SearchResultAdapter(StringUtils.RV_ITEM_IMG,
        R.layout.item_search_result, data);
    mRecyclerResult.setLayoutManager(new LinearLayoutManager(this));
    mRecyclerResult.setNestedScrollingEnabled(false);
    smartSearchResultRefreshContent.setEnableRefresh(false);
    smartSearchResultRefreshContent.setEnableLoadMore(false);
    mRecyclerResult.setAdapter(mSearchResultAdapter);
  }

  @Override public void setHotKey(String[] keys) {

  }

  @Override public void setHotKeyContent(int flag, List<SearchResult.DataBean.Datas> data) {
    mRecyclerResult.setNestedScrollingEnabled(true);
    smartSearchResultRefreshContent.setEnableRefresh(true);
    smartSearchResultRefreshContent.setEnableLoadMore(true);
    if (flag == StringUtils.TYPE_HOME_MORE_ARTICLE_LOAD) {
      this.mList = data;
      mSearchResultAdapter =
          new SearchResultAdapter(StringUtils.RV_ITEM_IMG, R.layout.item_search_result, data);
      mRecyclerResult.setLayoutManager(new LinearLayoutManager(this));
      mRecyclerResult.setAdapter(mSearchResultAdapter);
    } else {
      mSearchResultAdapter.addData(data);
      mSearchResultAdapter.notifyDataSetChanged();
    }
    mSearchResultAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
      @Override public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mSearchPresenter.getSelectedURL(mSearchResultAdapter.getData().get(position).getLink());
      }
    });
  }

  @Override public void collectArticle(int position, boolean isCollect) {

  }

  @Override public void goWedActivity(String URL) {
    Intent intent = new Intent(this, WebActivity.class);
    intent.putExtra("URL", URL);
    startActivity(intent);
    overridePendingTransition(R.anim.enter_fade_out, R.anim.enter_fade_in);
  }

  @Override public void setPresenter(Contact.SearchPresenter presenter) {

  }

  @Override public void onLeftClick(View v) {

  }

  @Override public void onTitleClick(View v) {

  }

  @Override public void onRightClick(View v) {

  }
}
