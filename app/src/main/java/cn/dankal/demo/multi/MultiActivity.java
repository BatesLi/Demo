package cn.dankal.demo.multi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;
import cn.dankal.demo.multi.adapter.MultiAdapter;
import cn.dankal.demo.multi.contact.MultiContact;
import cn.dankal.demo.multi.presenter.MultiPresenter;
import java.util.List;

public class MultiActivity extends AppCompatActivity implements MultiContact.MultiIView {

  @BindView(R.id.recycler_multi) RecyclerView mRecyclerMulti;
  public MultiAdapter multiAdapter;
  GridLayoutManager gridLayoutManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_multi);
    ButterKnife.bind(this);

    MultiPresenter presenter = new MultiPresenter();
    presenter.attachView(this);
    presenter.getRequestJson();

    gridLayoutManager = new GridLayoutManager(this, 6);
    //spanCount / getSpanSize = 每行的item数量
    gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
      @Override public int getSpanSize(int position) {
        switch (multiAdapter.getItemViewType(position)) {
          case MultiAdapter.ITEM_ONE:
          case MultiAdapter.ITEM_TWO:
            return 6;
          case MultiAdapter.ITEM_THREE:
            return 3;
        }
        return 6;
      }
    });

    mRecyclerMulti.setLayoutManager(gridLayoutManager);
  }

  @Override public void getMultiData(List<Integer> beans) {
    multiAdapter = new MultiAdapter(beans, this);
    mRecyclerMulti.setAdapter(multiAdapter);
  }

  @Override public void Succeed() {

  }
}
