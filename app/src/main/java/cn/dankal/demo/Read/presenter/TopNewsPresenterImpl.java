package cn.dankal.demo.Read.presenter;

import cn.dankal.demo.Read.app.AppConstants;
import cn.dankal.demo.Read.base.BasePresenter;
import cn.dankal.demo.Read.bean.NewsDetailBean;
import cn.dankal.demo.Read.bean.NewsListBean;
import cn.dankal.demo.Read.contact.TopNewsPresenter;
import cn.dankal.demo.Read.http.Stateful;
import cn.dankal.demo.Read.http.service.TopNewsService;
import cn.dankal.demo.Read.utils.Callback;
import cn.dankal.demo.Read.utils.NewsJsonUtils;
import cn.dankal.demo.Read.utils.OkHttpUtils;
import javax.inject.Inject;

public class TopNewsPresenterImpl extends BasePresenter<TopNewsPresenter.View>
implements TopNewsPresenter.Presenter{

  private TopNewsService mTopNewsService;

  @Inject
  public TopNewsPresenterImpl(TopNewsService topNewsService) {
    this.mTopNewsService = topNewsService;
  }

  @Override public void fetchTopNewsList(int id) {
    invoke(mTopNewsService.fetchNews(id),new Callback<NewsListBean>() {
      @Override
      public void onResponse(NewsListBean data) {
        checkState(data.getNewsList());
        mView.refreshView(data);
      }
    });
  }
  public void getDescribe(final String docid) {
    //地址值与值的区别
    String url = getDetailUrl(docid);
    OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
      @Override public void onSuccess(String response) {
        if (response != null) {
            if (mView instanceof Stateful) {
                ((Stateful)mView).setState(AppConstants.STATE_EMPTY);
            }
            NewsDetailBean newsDetailBean = NewsJsonUtils.readJsonNewsDetailBeans(response,docid);
            ((TopNewsPresenter.ViewActivity)mView).fetchActivityView(newsDetailBean);
        }
      }

      @Override public void onFailure(Exception e) {
      }
    };
    OkHttpUtils.get(url,loadNewsCallback);
  }
  //StringBuffer对比String来说，比较快？因为String对象的创建
  // ，该对象不可以更改。且对象在JVM是一个不断创建回收的过程.
  private String getDetailUrl(String docId) {
    StringBuffer sb = new StringBuffer("http://c.m.163.com/nc/article/");
    sb.append(docId).append("/full.html");
    return sb.toString();
  }
}

