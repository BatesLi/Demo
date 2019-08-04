package cn.dankal.demo.SearchPractise.SearchWanAndroid.presenter;

import cn.dankal.demo.SearchPractise.SearchWanAndroid.Api.Contact;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.Api.LoadTasksCallBack;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.Api.WebTask;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.Utils.StringUtils;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.base.BasePresenter;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.model.Data;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.model.SearchResult;
import java.util.ArrayList;
import java.util.List;

public class SearchPresenter extends BasePresenter implements Contact.SearchPresenter,
    LoadTasksCallBack {

  public Contact.SearchView mSearchView;
  public WebTask mWebTask;

  public SearchPresenter(Contact.SearchView searchView, WebTask webTask) {
    this.mSearchView = searchView;
    this.mWebTask = webTask;
  }

  public static SearchPresenter createPresenter(Contact.SearchView searchView, WebTask webTask) {
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
    mWebTask.execute(this, StringUtils.TYPE_HOTKEY_CONTENT_LOAD, hotkeys, String.valueOf(page));
  }

  @Override public void addHotKeyContent(String hotkeys, int page) {
    mWebTask.execute(this, StringUtils.TYPE_HOTKEY_CONTENT_ADD, hotkeys, String.valueOf(page));
  }

  @Override public void collectArticle(int ID, boolean isCollect, int position) {

  }

  @Override public void getSelectedURL(String URL) {
    mSearchView.goWedActivity(URL);
  }

  @Override public void onSuccess(Object object, int... params) {
    switch (params[0]) {
      case StringUtils.TYPE_HOT_KEY:
        List<Data> mList = (List<Data>) object;
        if (mList.size() != 0 || mList != null) {
          String[] keys = new String[mList.size()];
          for (int i = 0; i < mList.size(); i++) {
            keys[i] = mList.get(i).getName();
          }
          mSearchView.setHotKey(keys);
        }
        break;
      case StringUtils.TYPE_HOME_MORE_ARTICLE_LOAD:
      case StringUtils.TYPE_HOME_MORE_ARTICLE_ADD:
        SearchResult.DataBean dataBean = (SearchResult.DataBean) object;
        if (dataBean.getCurPage() != 0 && dataBean.getDatas() != null) {
          mSearchView.setHotKeyContent(params[0], dataBean.getDatas());
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
