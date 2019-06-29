package cn.dankal.demo.SQLite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.dankal.basic_lib.util.ToastUtil;
import cn.dankal.demo.R;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BatesLi
 * @date
 * @org
 * @email
 * @describe 练习
 */

public class SQLiteActivity extends AppCompatActivity {

  public List<DBSQLBean> mDbsqlBeanList;
  public SQLiteAdapter sqLiteAdapter;
  public DBSQLDao dbsqlDao;
  @BindView(R.id.edit_sql) EditText editSql;
  @BindView(R.id.tv_sqlmsg) TextView txtSqlmsg;
  @BindView(R.id.list_view_sqlite) ListView listViewSqlite;
  @BindViews({R.id.btn_execute, R.id.btn_insert, R.id.btn_delete, R.id.btn_update,
      R.id.btn_query1, R.id.btn_query2, R.id.btn_query3}) List<Button> buttonList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sqlite);
    ButterKnife.bind(this);
    addListViewHead();

    dbsqlDao = new DBSQLDao(this);
    dbsqlDao.initTable();

    mDbsqlBeanList = dbsqlDao.getAllDaoData();
    if (mDbsqlBeanList != null) {
      sqLiteAdapter = new SQLiteAdapter(this, mDbsqlBeanList);
      listViewSqlite.setAdapter(sqLiteAdapter);
    }
  }

  @OnClick({R.id.btn_execute, R.id.btn_insert, R.id.btn_delete, R.id.btn_update,
      R.id.btn_query1, R.id.btn_query2, R.id.btn_query3})
  public void onSQLiteButtonClick(View view) {
    switch (view.getId()) {
      case R.id.btn_execute:
        ToastUtil.toToast("点击");
        break;
      case R.id.btn_insert:
        if (dbsqlDao.insertData()) {
          txtSqlmsg.setVisibility(View.VISIBLE);
          txtSqlmsg.setText("新增一条数据");
          refreshDbList();
        } else {
          ToastUtil.toToast("新增数据失败");
        }
        break;
      case R.id.btn_delete:
        if (dbsqlDao.deleteData()) {
          txtSqlmsg.setVisibility(View.VISIBLE);
          txtSqlmsg.setText("删除一条数据");
          refreshDbList();
        } else {
          ToastUtil.toToast("删除数据失败");
        }
        break;
      case R.id.btn_update:
        if (dbsqlDao.upData()) {
          txtSqlmsg.setVisibility(View.VISIBLE);
          txtSqlmsg.setText("更新数据");
          refreshDbList();
        } else {
          ToastUtil.toToast("更新失败");
        }
        break;
      default:
        break;
    }
  }

  /**
   * 添加一个listView的header
   */
  private void addListViewHead() {
    listViewSqlite.addHeaderView(LayoutInflater.from(this)
        .inflate(R.layout.item_sql_header, null, false));
  }

  /**
   * 更新布局数据
   */
  private void refreshDbList() {
    mDbsqlBeanList.clear();
    mDbsqlBeanList.addAll(dbsqlDao.getAllDaoData());
    sqLiteAdapter.notifyDataSetChanged();
  }
}
