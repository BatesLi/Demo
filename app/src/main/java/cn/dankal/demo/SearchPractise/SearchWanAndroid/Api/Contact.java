package cn.dankal.demo.SearchPractise.SearchWanAndroid.Api;

import cn.dankal.demo.SearchPractise.SearchWanAndroid.base.BaseView;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.model.SearchResult;
import java.util.List;

public interface Contact {

  interface SearchPresenter {
    void getHotKey();

    void initView();

    void getHotKeyContent(String hotkeys, int page);

    void addHotKeyContent(String hotkeys, int page);

    void collectArticle(int ID, boolean isCollect, int position);

    void getSelectedURL(String URL);
  }

  interface SearchView extends BaseView<Contact.SearchPresenter> {
    void initView(List<SearchResult.DataBean.Datas> data);

    void setHotKey(String[] keys);

    void setHotKeyContent(int flag, List<SearchResult.DataBean.Datas> data);

    void collectArticle(int position, boolean isCollect);

    void goWedActivity(String URL);
  }
}
