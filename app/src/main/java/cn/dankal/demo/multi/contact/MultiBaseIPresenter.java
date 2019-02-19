package cn.dankal.demo.multi.contact;

import android.support.annotation.NonNull;

public interface MultiBaseIPresenter<V extends MultiBaseIView> {
  void attachView(@NonNull V view);
}
