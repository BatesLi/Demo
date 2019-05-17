package cn.dankal.demo.Read.contact;

import cn.dankal.demo.Read.base.BaseView;
import cn.dankal.demo.Read.bean.GankIoDataBean;
import java.util.List;

public interface GankIoAndoridPresenter{
  interface View extends BaseView<List<GankIoDataBean.ResultBean>>{}
  interface Presenter{
    void FetchGankIoData(int page,int pre_page);
  }
}
