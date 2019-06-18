package cn.dankal.demo.SearchPractise;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;
import cn.dankal.demo.SearchPractise.flowlayout.FlowLayout;
import cn.dankal.demo.SearchPractise.flowlayout.TagAdapter;
import cn.dankal.demo.SearchPractise.flowlayout.TagFlowLayout;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BatesLi
 * @date
 * @org
 * @email
 * @describe
 */

public class SearchOneActivity extends AppCompatActivity {

  private RecordDa mRecordDa;
  //TagFlowLayout的adapter与标签样式
  private List<String> mRecordList = new ArrayList<>();
  private TagAdapter mTagAdapter;
  //默认展示词条个数
  private static final int DEFAULT_RECORD_NUMBER = 10;

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
    //默认账号???
    String username = "a";
    mRecordDa = new RecordDa(this, username);
    initData();
    initView();
  }

  /**
   * RxJava的使用
   */
  private void initData() {
    Observable.create(new ObservableOnSubscribe<List<String>>() {
      @Override public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {
        emitter.onNext(mRecordDa.getRecordByNumber(DEFAULT_RECORD_NUMBER));
      }
    }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<String>>() {
          @Override public void accept(List<String> strings) throws Exception {
            mRecordList.clear();
            mRecordList = strings;
            if (null == mRecordList || mRecordList.size() == 0) {
              linearHistorySearch.setVisibility(View.GONE);
            } else {
              linearHistorySearch.setVisibility(View.VISIBLE);
            }
            if (mTagAdapter != null) {
              mTagAdapter.setData(mRecordList);
              mTagAdapter.notifyDataChanged();
            }
          }
        });
  }

  private void initView() {
    //数据监听接口的回调
    mRecordDa.setNotifyDataChanged(new RecordDa.NotifyDataChanged() {
      @Override public void notifyDataChanged() {
        initData();
      }
    });
    /*
     * 创建历史标签适配器，设置标签对应的内容
     * */
    mTagAdapter = new TagAdapter<String>(mRecordList) {
      @Override public View getView(FlowLayout parent, int position, String s) {
        TextView txtHistory =
            (TextView) LayoutInflater.from(SearchOneActivity.this).inflate(R.layout.tv_history,
                flSearchRecords, false);
        //为标签设置对应的内容
        txtHistory.setText(s);
        return txtHistory;
      }
    };
    //设置flowLayout的Adapter
    flSearchRecords.setAdapter(mTagAdapter);
    //setOnTagClickListener，当点击某个Tag回调
    flSearchRecords.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
      @Override public void onTagClick(View view, int position, FlowLayout parent) {
        editQuery.setText(mRecordList.get(position));
        editQuery.setSelection(editQuery.length());
      }
    });
    //添加搜索记录的逻辑
    txtSearch.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        String history = editQuery.getText().toString();
        if (!TextUtils.isEmpty(history)) {
          //添加数据
          mRecordDa.addRecords(history);
        }
      }
    });
    //view的回调
    flSearchRecords.getViewTreeObserver().addOnGlobalLayoutListener(
        new ViewTreeObserver.OnGlobalLayoutListener() {
          @Override public void onGlobalLayout() {
            boolean isOverFlow = flSearchRecords.isOverFlow();
            boolean isLimit = flSearchRecords.isLimit();
            if (isOverFlow && isLimit) {
                imgArrow.setVisibility(View.VISIBLE);
            }else {
                imgArrow.setVisibility(View.GONE);
            }
          }
        });
    //清除用户指定的所有记录
    imgClearAllRecords.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        showDialog("确定需要删除全部记录吗", new DialogInterface.OnClickListener() {
          @Override public void onClick(DialogInterface dialog, int which) {
            flSearchRecords.setLimit(true);
            mRecordDa.deleteUsernameAllRecord();
          }
        });
      }
    });
    //清除搜索历史
    imgClearSearch.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        editQuery.setText("");
      }
    });
  }

  private void showDialog(String dialogTitle,
      @NonNull DialogInterface.OnClickListener onClickListener) {
    AlertDialog.Builder builder = new AlertDialog.Builder(SearchOneActivity.this);
    builder.setMessage(dialogTitle);
    builder.setPositiveButton("确定", onClickListener);
    builder.setNegativeButton("取消", null);
    builder.create().show();
  }

  @Override
  protected void onDestroy() {
    mRecordDa.closeDatabase();
    mRecordDa.removeNotifyDatabaseChanged();
    super.onDestroy();
  }
}





