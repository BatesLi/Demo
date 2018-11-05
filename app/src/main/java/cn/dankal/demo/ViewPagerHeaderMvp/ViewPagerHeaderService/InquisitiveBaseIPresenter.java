package cn.dankal.demo.ViewPagerHeaderMvp.ViewPagerHeaderService;

import android.support.annotation.NonNull;

public interface InquisitiveBaseIPresenter<V extends InquisitiveBaseIView> {
  void attachView(@NonNull V view);
}
