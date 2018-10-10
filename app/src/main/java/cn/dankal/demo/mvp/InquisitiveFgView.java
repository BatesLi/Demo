package cn.dankal.demo.mvp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

interface InquisitiveView {

  void setDataRefresh(Boolean refresh);

  RecyclerView getRecyclerView();

  LinearLayoutManager getLayoutManager();
}
