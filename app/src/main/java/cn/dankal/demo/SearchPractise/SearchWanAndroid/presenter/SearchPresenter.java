package cn.dankal.demo.SearchPractise.SearchWanAndroid.presenter;

import cn.dankal.demo.SearchPractise.SearchWanAndroid.Api.Contract;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.Api.LoadTasksCallBack;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.Api.WebTask;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.Utils.StringUtils;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.base.BasePresenter;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.model.Data;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.model.SearchResult;
import java.util.ArrayList;
import java.util.List;

public class SearchPresenter extends BasePresenter implements Contract.SearchPresenter,
    LoadTasksCallBack {

  public Contract.SearchView mSearchView;
  private WebTask mWebTask;

  public SearchPresenter(Contract.SearchView searchView, WebTask webTask) {
    this.mSearchView = searchView;
    this.mWebTask = webTask;
  }

  public static SearchPresenter createPresenter(Contract.SearchView searchView, WebTask webTask) {
    return new SearchPresenter(searchView, webTask);
  }

  @Override public void getHotKey() {
    mWebTask.execute(this, StringUtils.TYPE_HOT_KEY);
  }

  @Override public void initView() {
    List<SearchResult.DataBean.Datas> resultList = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
      resultList.add(new SearchResult.DataBean.Datas());
    }
    mSearchView.initView(resultList);
  }

  @Override public void getHotKeyContent(String hotkeys, int page) {

  }

  @Override public void addHotKeyContent(String hotkeys, int page) {

  }

  @Override public void collectArticle(int ID, boolean isCollect, int position) {

  }

  @Override public void getSelectedURL(String URL) {

  }

  @Override public void onSuccess(Object o, int... params) {
    switch (params[0]) {
      case StringUtils.TYPE_HOT_KEY:
        List<Data> mList = (List<Data>) o;
        if (mList.size() != 0 || mList != null) {
          String[] keys = new String[mList.size()];
          for (int i = 0; i < mList.size(); i++) {
            keys[i] = mList.get(i).getName();
          }
          mSearchView.setHotKey(keys);
        }
        break;
    }
  }

  @Override public void onStart() {

  }

  @Override public void onFailed() {

  }

  @Override public void onError(int code, String msg) {

  }

  @Override public void onFinish() {

  }
}
