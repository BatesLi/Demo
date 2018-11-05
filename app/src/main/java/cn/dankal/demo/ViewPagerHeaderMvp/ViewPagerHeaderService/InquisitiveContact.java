package cn.dankal.demo.ViewPagerHeaderMvp.ViewPagerHeaderService;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public interface InquisitiveContact {

  interface InquisitiveIView extends InquisitiveBaseIView {
    RecyclerView getRecyclerView();

    LinearLayoutManager getLinearManager();
  }

  interface InquisitiveIPresenter
      extends InquisitiveBaseIPresenter<InquisitiveContact.InquisitiveIView> {
    void getData();
  }
}
