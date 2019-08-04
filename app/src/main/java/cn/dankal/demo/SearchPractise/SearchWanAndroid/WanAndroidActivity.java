package cn.dankal.demo.SearchPractise.SearchWanAndroid;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.Api.Contact;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.Api.WebTask;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.base.BaseActivity;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.custom.ClearEditText;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.model.SearchResult;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.presenter.SearchPresenter;
import cn.dankal.demo.SearchPractise.flowlayout.FlowLayout;
import cn.dankal.demo.SearchPractise.flowlayout.TagAdapter;
import cn.dankal.demo.SearchPractise.flowlayout.TagFlowLayout;
import com.gyf.immersionbar.ImmersionBar;
import java.util.List;

/**
 * @author BatesLi
 * @date
 * @org
 * @email
 * @describe 玩Android的search模块实现类
 */

public class WanAndroidActivity extends BaseActivity implements Contact.SearchView {

  public Contact.SearchPresenter mPresenter;
  @BindView(R.id.edit_wan_search) ClearEditText editWanSearch;
  @BindView(R.id.img_btn_wan_search) ImageButton imgBtnWanSearch;
  @BindView(R.id.flow_layout_wan_search) TagFlowLayout flowLayoutWanSearch;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_wan_andorid_search);
    ButterKnife.bind(this);

    ImmersionBar.with(this).statusBarColor(R.color.bg_daily_mode).autoDarkModeEnable(true)
        .fitsSystemWindows(true).keyboardEnable(true).init();
    if (mPresenter == null) {
      mPresenter = SearchPresenter.createPresenter(this, WebTask.getInstance());
    }
    mPresenter.getHotKey();//数据

    imgBtnWanSearch.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (!TextUtils.isEmpty(editWanSearch.getText().toString())) {
          Intent intent = new Intent(v.getContext(), SearchResultActivity.class);
          intent.putExtra("HOT_KEY", editWanSearch.getText().toString());
          startActivity(intent);
          finish();
        }
      }
    });
  }

  @Override public void setHotKey(String[] keys) {
    final LayoutInflater layoutInflater = LayoutInflater.from(this);
    flowLayoutWanSearch.setAdapter(new TagAdapter<String>(keys) {
      @Override public View getView(FlowLayout parent, int position, String s) {
        TextView tv = (TextView) layoutInflater.inflate(R.layout.item_flow_txt,
            flowLayoutWanSearch, false);
        tv.setText(s);
        return tv;
      }
    });
    flowLayoutWanSearch.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
      @Override public void onTagClick(View view, int position, FlowLayout parent) {
        if (TextUtils.isEmpty(editWanSearch.getText().toString())) {
          editWanSearch.setText(keys[position]);
        } else {
          editWanSearch.setText(editWanSearch.getText().append("" + keys[position]));
        }
      }
    });
  }

  @Override public void setPresenter(Contact.SearchPresenter presenter) {
    this.mPresenter = presenter;
  }

  @Override public void setHotKeyContent(int flag, List<SearchResult.DataBean.Datas> data) {

  }

  @Override public void collectArticle(int position, boolean isCollect) {

  }

  @Override public void goWedActivity(String URL) {

  }

  @Override public void initView(List data) {

  }
}
