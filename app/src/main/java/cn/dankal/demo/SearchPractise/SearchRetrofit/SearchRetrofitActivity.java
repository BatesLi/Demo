package cn.dankal.demo.SearchPractise.SearchRetrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.dankal.demo.R;

/**
 * @author BatesLi
 * @date
 * @org
 * @email
 * @describe
 */

public class SearchRetrofitActivity extends AppCompatActivity {

  @BindView(R.id.edi_search_retrofit) EditText ediSearchRetrofit;
  @BindView(R.id.img_clear_text) ImageView imgClearText;
  @BindView(R.id.txt_cancel) TextView txtCancel;
  @BindView(R.id.relative_action_bar) RelativeLayout relativeActionBar;
  @BindView(R.id.relative_search_top) RelativeLayout relativeSearchTop;
  @BindView(R.id.recycler_search_top) RecyclerView recyclerSearchTop;
  @BindView(R.id.view_bottom_line) View viewBottomLine;
  @BindView(R.id.img_clear_history) ImageView imgClearHistory;
  @BindView(R.id.relative_search_history) RelativeLayout relativeSearchHistory;
  @BindView(R.id.iv_history_recycle) RecyclerView ivHistoryRecycle;
  @BindView(R.id.relative_normal) RelativeLayout relativeNormal;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search_retrofit);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.txt_cancel)
  public void Cancel(View view) {
    finish();
  }
}
