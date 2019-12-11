package cn.dankal.demo.SearchPractise.SearchWanAndroid.Api;

import cn.dankal.demo.SearchPractise.SearchWanAndroid.base.BaseView;

public interface HomeContact {
    interface HomePresenter {
        void getData(String url);
    }

    interface HomeView extends BaseView<HomePresenter> {
        void setData(String url);
    }
}
