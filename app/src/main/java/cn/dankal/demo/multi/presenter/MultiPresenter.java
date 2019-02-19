package cn.dankal.demo.multi.presenter;

import android.support.annotation.NonNull;
import cn.dankal.demo.multi.contact.MultiContact;
import java.util.ArrayList;
import java.util.List;

public class MultiPresenter implements MultiContact.MultiIPresenter {

  MultiContact.MultiIView iView;
  public List<Integer> multiBeans;

  @Override public void getRequestJson() {
    multiBeans = new ArrayList<>();
    for (int i = 0; i < 15; i++) {
      multiBeans.add(i);
    }
    iView.getMultiData(multiBeans);
  }

  @Override public void attachView(@NonNull MultiContact.MultiIView view) {
    this.iView = view;
  }
}
