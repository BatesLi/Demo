package cn.dankal.demo.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import cn.dankal.demo.R;

public class SqliteActivity extends AppCompatActivity implements View.OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sqlite);

    //根据Layout按钮id得到Java按钮对象
    Button insert = (Button) findViewById(R.id.btn_execute_insert);
    Button insert_cleardata = (Button) findViewById(R.id.btn_insert_clear_data);

    Button update = (Button) findViewById(R.id.btn_execute_update);
    Button update_cleardata = (Button) findViewById(R.id.btn_update_clear_data);

    Button delete = (Button) findViewById(R.id.btn_execute_delete);
    Button delete_cleardata = (Button) findViewById(R.id.btn_delete_clear_data);

    Button query = (Button) findViewById(R.id.btn_query_all);
    Button clearquery = (Button) findViewById(R.id.btn_clear_query);

    //为所有按钮对象设置监听器
    insert.setOnClickListener(this);
    insert_cleardata.setOnClickListener(this);

    update.setOnClickListener(this);
    update_cleardata.setOnClickListener(this);

    delete.setOnClickListener(this);
    delete_cleardata.setOnClickListener(this);

    query.setOnClickListener(this);
    clearquery.setOnClickListener(this);
  }

  @Override public void onClick(View v) {
    EditText insert_edittext = (EditText) findViewById(R.id.edit_input_insert);
    String insert_data = insert_edittext.getText().toString();

    EditText delete_edittext = (EditText) findViewById(R.id.edit_input_delete_content);
    String delete_data = delete_edittext.getText().toString();

    EditText update_before_edittext = (EditText) findViewById(R.id.edit_update_before);
    String update_before_data = update_before_edittext.getText().toString();
    EditText update_after_edittext = (EditText) findViewById(R.id.edit_update_after);
    String update_after_data = update_after_edittext.getText().toString();

    TextView textview = (TextView) findViewById(R.id.txt_hint_view);

    DatabaseHelper databaseHelper = new DatabaseHelper(SqliteActivity.this
        , "test_db", null, 1);
    SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

    switch (v.getId()) {
      //插入数据
      case R.id.btn_execute_insert:
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", insert_data);
        sqLiteDatabase.insert("user", null, contentValues);
        break;
      //插入数据后面的清除数据按钮
      case R.id.btn_insert_clear_data:
        insert_edittext.setText("");
        break;
      //执行更新
      case R.id.btn_execute_update:
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("name", update_after_data);
        sqLiteDatabase.update("user", contentValues2, "name = ?",
            new String[] {update_before_data});
        break;
      //执行更新后面的清除按钮
      case R.id.btn_update_clear_data:
        update_after_edittext.setText("");
        update_before_edittext.setText("");
        break;
      //输入内容执行删除
      case R.id.btn_execute_delete:
        sqLiteDatabase.delete("user", "name=?", new String[] {delete_data});
        break;
      //执行删除后面的 清除按钮
      case R.id.btn_delete_clear_data:
        delete_edittext.setText("");
        break;
      //查询全部按钮
      case R.id.btn_query_all:
        Cursor cursor = sqLiteDatabase.query("user", new String[] {"name"}
            , null, null, null, null, null);
        String textview_data = "";
        while (cursor.moveToNext()) {
          //getColumnIndex()返回指定列的名称，如果不存在返回-1
          String name = cursor.getString(cursor.getColumnIndex("name"));
          textview_data = textview_data + "\n" + name;
        }
        textview.setText(textview_data);
        break;
      //查询全部按钮下面的清除查询按钮
      case R.id.btn_clear_query:
        textview.setText("");
        textview.setHint("查询内容为空");
        break;
      default:
        break;
    }
  }
}
