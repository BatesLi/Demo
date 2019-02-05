package cn.dankal.demo.RecyclerComplexityView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;
import cn.dankal.demo.RecyclerComplexityView.adapter.RecyclerComplexityAdapter;
import cn.dankal.demo.RecyclerComplexityView.contact.RecyclerComplexityContact;
import cn.dankal.demo.RecyclerComplexityView.presenter.RecyclerComplexityPresenter;
import java.util.List;

public class RecyclerComplexityActivity extends AppCompatActivity implements
    RecyclerComplexityContact.RecyclerComplexityIView {

  @BindView(R.id.recycler_complexity) RecyclerView mRecyclerComplexity;

  public RecyclerComplexityAdapter mComplexityAdapter;
  public GridLayoutManager mGridLayoutManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recycler_complexity);
    ButterKnife.bind(this);

    RecyclerComplexityPresenter presenter = new RecyclerComplexityPresenter();
    presenter.attachView(this);
    presenter.requestJsonComplexity();
    mGridLayoutManager = new GridLayoutManager(this, 6, GridLayoutManager.VERTICAL,
        false);
    /** * 根据GridLayoutManager的getSpanSize方法可以动态的设置item跨列数
     * 需要设置：4个参数的GridLayoutManager
     * new GridLayoutManager(getActivity(),6,GridLayoutManager.VERTICAL,false);
     * 这里的6（自己设置的最好设置成偶数）就相当于分母，6默认显示一整行（1列），
     下面的3 和2 就相当于分子，返回3就是（1/2）所以此类型对应的是2列，返回2就是（1/3）所以此类型对应的是3列
     例如：根据getItemViewType返回的itemType，第一个position显示为轮播图,所以返回的是
     gridManager.getSpanCount();（即：6）
     * */
    mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
      @Override public int getSpanSize(int position) {
        switch (mComplexityAdapter.getItemViewType(position)) {
          case RecyclerComplexityAdapter.ITEM_PRODUCT:
            return 2;
          case RecyclerComplexityAdapter.ITEM_OTHER:
            return 3;
          default:
            return 6;
        }
      }
    });
    mRecyclerComplexity.setLayoutManager(mGridLayoutManager);
  }

  @Override public void getComplexityData(List<Object> objectList) {
    mComplexityAdapter = new RecyclerComplexityAdapter(this, objectList);
    mRecyclerComplexity.setAdapter(mComplexityAdapter);
    mComplexityAdapter.notifyDataSetChanged();
  }

  @Override public void Succeed() {

  }
}
