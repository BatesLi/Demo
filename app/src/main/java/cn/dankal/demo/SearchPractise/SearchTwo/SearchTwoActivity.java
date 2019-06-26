package cn.dankal.demo.SearchPractise.SearchTwo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;

/**
 * @author BatesLi
 * @date
 * @org
 * @email
 * @describe Android搜索实时显示功能实现
 */

public class SearchTwoActivity extends AppCompatActivity {

  @BindView(R.id.edi_search) EditText mEdiSearch;
  @BindView(R.id.txt_search) TextView mTxtSearch;
  @BindView(R.id.txt_tip) TextView mTxtTip;
  @BindView(R.id.list_view_record) ListViewForScrollView mListViewRecord;
  @BindView(R.id.txt_clear) TextView mTxtClear;

  SimpleCursorAdapter adapter;
  SearchSQLiteHelp searchSQLiteHelp;
  RecordSQLiteHelp recordSQLiteHelp;
  SQLiteDatabase db_search;
  SQLiteDatabase db_record;
  Cursor cursor;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search_two);
    ButterKnife.bind(this);

    initData();
    initListener();
  }

  private void initData() {
    //context的传递，不可以用全局的Context
    searchSQLiteHelp = new SearchSQLiteHelp(this);
    recordSQLiteHelp = new RecordSQLiteHelp(this);
    //初始化本地数据库
    initializeData();
    //尝试从保存查询纪录的数据库中获取历史纪录并显示
    cursor = recordSQLiteHelp.getReadableDatabase().rawQuery("select * from table_records", null);
    adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor,
        new String[] {"username", "password"}, new int[] {android.R.id.text1, android.R.id.text2},
        CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    mListViewRecord.setAdapter(adapter);
  }

  /**
   * 避免重复初始化数据
   */
   /* private void deleteData() {
      db_search = searchSQLiteHelp.getWritableDatabase();
      db_search.execSQL("delete from table_search");
      db_search.close();
    }*/

  /**
   * 初始化数据
   */
  protected void initializeData() {
    db_search = searchSQLiteHelp.getWritableDatabase();
    for (int i = 0; i < 20; i++) {
      db_search.execSQL("insert into table_search values(null,?,?) ",
          new String[] {"name" + i + 10, "pass" + i + "word"});
    }
    db_search.close();
  }

  /**
   * 初始化事件监听
   */
  private void initListener() {

    /**
     *edit对键盘搜索按钮的监听，保存搜索记录，隐藏键盘
     * onKey()方法包含三个参数，第一个参数是拦截到此事件的对话框对象的引用
     * 第二个参数是此事件对应的keyCode，第三个参数是此事件对象本身。
     * @return
     */
    mEdiSearch.setOnKeyListener(new View.OnKeyListener() {
      @Override public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
          //隐藏键盘
          ((InputMethodManager) MyApplication.getContext()
              .getSystemService(Context.INPUT_METHOD_SERVICE))
              .hideSoftInputFromWindow(SearchTwoActivity.this.getCurrentFocus().getWindowToken()
                  , InputMethodManager.HIDE_NOT_ALWAYS);
          insertRecord(mEdiSearch.getText().toString().trim());
        }
        return false;
      }
    });
    /**
     实现模糊查询
     addTextChangedListener 用它来监听用户输入状态
     */
    mEdiSearch.addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      //after很简单，这个方法就是在EditText内容已经改变之后调用
      @Override public void afterTextChanged(Editable s) {
        if (mEdiSearch.getText().toString().equals("")) {
          mTxtTip.setText("搜索历史");
          mTxtClear.setVisibility(View.VISIBLE);
          cursor = recordSQLiteHelp.getReadableDatabase().rawQuery("select * from table_records",
              null);
          refreshListView();
        } else {
          mTxtTip.setText("搜索结果");
          mTxtClear.setVisibility(View.GONE);
          String searchString = mEdiSearch.getText().toString();
          queryData(searchString);
        }
      }
    });
    mTxtSearch.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        insertRecord(mEdiSearch.getText().toString().trim());
      }
    });
    mTxtClear.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        deleteRecord();
      }
    });
  }

  /**
   * 保存搜索记录
   */
  protected void insertRecord(String username) {
    if (!isRecord(username)) {
      db_record = recordSQLiteHelp.getReadableDatabase();
      db_record.execSQL("insert into table_records values(null,?,?)", new String[] {username, ""});
      db_record.close();
    }
  }

  /**
   * 检查是否存在搜索记录
   */
  private boolean isRecord(String records) {
    cursor = recordSQLiteHelp.getReadableDatabase()
        .rawQuery("select _id,username from table_records where username = ?",
            new String[] {records});
    return cursor.moveToNext();
  }

  /**
   * 刷新listView
   */
  private void refreshListView() {
    adapter.notifyDataSetChanged();
    adapter.swapCursor(cursor);
  }

  /**
   * 搜索数据库中的数据
   */
  private void queryData(String searchData) {
    cursor = searchSQLiteHelp.getReadableDatabase()
        .rawQuery("select * from table_search where username like'%"
            + searchData + "%' or password like '%" + searchData + "%'", null);
    refreshListView();
  }

  /**
   * 删除历史记录
   */
  private void deleteRecord() {
    db_record = recordSQLiteHelp.getWritableDatabase();
    db_record.execSQL("delete from table_records");
    cursor = recordSQLiteHelp.getReadableDatabase().rawQuery("select * from table_records", null);
    if (mEdiSearch.getText().toString().equals("")) {
      refreshListView();
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    if (db_record != null) {
      db_record.close();
    }
    if (db_search != null) {
      db_search.close();
    }
  }
}





