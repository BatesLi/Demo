package cn.dankal.demo.multi.contact;

import java.util.List;

public interface MultiContact {
  interface MultiIView extends MultiBaseIView {
    void getMultiData(List<Integer> beans);
  }

  interface MultiIPresenter extends MultiBaseIPresenter<MultiIView> {
    void getRequestJson();
  }
}
