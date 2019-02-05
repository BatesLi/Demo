package cn.dankal.demo.RecyclerComplexityView.base;

import android.support.annotation.NonNull;

public interface BaseComplexityIPresenter<V extends BaseComplexityIView> {
  void attachView(@NonNull V view);
}
