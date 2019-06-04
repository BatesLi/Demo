package cn.dankal.demo.SearchPractise;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;
import cn.dankal.demo.SearchPractise.flowlayout.TagFlowLayout;

public class SearchOneActivity extends AppCompatActivity {

  @BindView(R.id.cl_toolbar) ConstraintLayout clToolbar;
  @BindView(R.id.img_back) ImageView imgBack;
  @BindView(R.id.edit_query) EditText editQuery;
  @BindView(R.id.img_clear_search) ImageView imgClearSearch;
  @BindView(R.id.txt_search) TextView txtSearch;
  @BindView(R.id.linear_history_search) LinearLayout linearHistorySearch;
  @BindView(R.id.txt_history_hint) TextView txtHistoryHint;
  @BindView(R.id.img_clear_all_records) ImageView imgClearAllRecords;
  @BindView(R.id.fl_search_records) TagFlowLayout flSearchRecords;
  @BindView(R.id.img_arrow) ImageView imgArrow;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search_one);
    ButterKnife.bind(this);
  }
}
