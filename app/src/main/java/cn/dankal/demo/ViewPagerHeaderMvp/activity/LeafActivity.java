package cn.dankal.demo.ViewPagerHeaderMvp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.design.widget.TabLayout;
import butterknife.BindView;
import cn.dankal.demo.R;
import cn.dankal.demo.ViewPagerHeaderMvp.fragment.BlankFragment;
import cn.dankal.demo.ViewPagerHeaderMvp.fragment.InquisitiveFragment;
import cn.dankal.demo.ViewPagerHeaderMvp.adapter.ViewPagerFgAdapter;
import cn.dankal.demo.ViewPagerHeaderMvp.base.BaseActivity;
import cn.dankal.demo.ViewPagerHeaderMvp.base.BasePresenter;
import java.util.ArrayList;
import java.util.List;

//java回调机制 一个类持有一个接口对象以及该接口的实现类实例，
// 就可以通过这个接口回调该实现类中的具体方法。
public class LeafActivity extends BaseActivity {

  @BindView(R.id.tab_leaf) TabLayout mTabLeaf;
  @BindView(R.id.viewPager_content_leaf) ViewPager mViewPagerContentLeaf;

  @Override protected BasePresenter createPresenter() {
    return null;
  }

  @Override protected int provedContentViewId() {
    return R.layout.activity_leaf;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initTabView();
  }

  private void initTabView() {
    List<Fragment> baseFragmentList = new ArrayList<>();
    baseFragmentList.add(new BlankFragment());
    baseFragmentList.add(new BlankFragment());
    baseFragmentList.add(new InquisitiveFragment());
    mViewPagerContentLeaf.setOffscreenPageLimit(3);
    mViewPagerContentLeaf.setAdapter(
        new ViewPagerFgAdapter(getSupportFragmentManager(), baseFragmentList,
            "main_view_pager"));
    mTabLeaf.setupWithViewPager(mViewPagerContentLeaf);
  }
}


















