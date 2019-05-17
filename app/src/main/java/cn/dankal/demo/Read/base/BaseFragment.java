package cn.dankal.demo.Read.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.dankal.demo.Read.http.LifeSubscription;
import cn.dankal.demo.Read.http.Stateful;
import cn.dankal.demo.Read.viewHelp.LoadingPage;
import com.chad.library.adapter.base.BaseQuickAdapter;
import javax.inject.Inject;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment
implements LifeSubscription, Stateful {
  @Inject
  protected P mPresenter;
  @Inject
  protected BaseQuickAdapter mAdapter; //一个封装好的工具类，减少adapter的代码量
  public LoadingPage mLoadingPage;
  private boolean mIsVisible = false;
  private boolean isPrepared = false;
  private boolean isFirst = true;

  protected View contentView;
  private Unbinder bind;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater
      , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    if (mLoadingPage == null) {
        mLoadingPage = new LoadingPage(getContext()) {
          @Override protected int getLayoutId() {
            return BaseFragment.this.getLayoutId();
          }

          @Override protected void initView() {
            if (isFirst) {
              //this为什么可以调用其余类的属性,找不到联系的点
              //答：创建一个类，并实现类里面的抽象方法，在实现的方法里面相当于调用本类的属性
                BaseFragment.this.contentView = this.contentView;
                bind = ButterKnife.bind(BaseFragment.this,contentView);
                BaseFragment.this.initView();
                isFirst = false;
            }
          }
          @Override protected void loadData() {
            BaseFragment.this.loadData();
          }
        };
    }
    isPrepared = true;
    loadBaseData();
    return mLoadingPage;
  }
  //实现数据fragment的缓加载
  @Override
  public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (getUserVisibleHint()) {
        mIsVisible = true;
        onVisible();
    }else {
        mIsVisible = false;
        onInVisible();
    }
  }
  @Override
  public void setState(int state) {
    mLoadingPage.state = state;
    mLoadingPage.showPage();
  }
  private void onInVisible() {
  }
  //显示时加载数据，先声明isPrepared，并初始化
  //生命周期会先执行setUserVisibleHint，再去执行onActivityCrated,
  //并在onActivityCreated之后第一次显示加载数据，只是加载一次。
  protected void onVisible() {
      if (isFirst) {
          initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
      }
      loadBaseData();
  }

  private void loadBaseData() {
    if (!isFirst || !isPrepared || ! mIsVisible) {
        return;
    }
    loadData();
  }
  private CompositeSubscription mCompositeSubscription;

  //添加rx的监听在onDestroy记得关闭，要不然内存泄漏
  @Override
  public void bindSubscription(Subscription subscription) {
    if (this.mCompositeSubscription == null) {
      this.mCompositeSubscription = new CompositeSubscription();
    }
    this.mCompositeSubscription.add(subscription);
  }
  @Override
  public void onDetach() {
    super.onDetach();
    if (bind != null) {
        bind.unbind();
    }
    if (mCompositeSubscription!= null && mCompositeSubscription.hasSubscriptions()) {
        mCompositeSubscription.unsubscribe();
    }
    if (mPresenter != null) {
        mPresenter.detachView();
    }
  }
  //网络请求成功在去加载布局
  protected abstract int getLayoutId();
  //loadData()和initView只执行一次，如果有一些请求需要二次的不要放到loadData()里面。
  //子类关于View的操作都应该在这里
  protected abstract void initView();
  //根据网络获取的数据返回状态，每一个子类的获取网络返回的都不一样，所以要交给子类去完成
  protected abstract void loadData();
   /*dagger2注入*/
  protected abstract void initInject();
}
