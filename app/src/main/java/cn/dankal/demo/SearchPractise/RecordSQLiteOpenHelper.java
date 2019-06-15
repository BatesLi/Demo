package cn.dankal.demo.SearchPractise;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
* 构造函数，调用父类 SQLiteOpenHelper 的构造函数。这个方法需要四个参数：
* 上下文环境（例如，一个 Activity），数据库名字(name)，一个可选的游标工厂（通常是 Null）
* ，一个代表你正在使用的数据库模型版本的整数(version：当前数据库的版本，值必须是整数并且是递增的状态)。
onCreate（）方法，它需要一个 SQLiteDatabase 对象作为参数，根据需要对这个对象填充表和初始化数据。
onUpgrade() 方法，它需要三个参数，一个 SQLiteDatabase 对象
，一个旧的版本号和一个新的版本号，这样你就可以清楚如何把一个数据库从旧的模型转变到新的模型。
* */

public class RecordSQLiteOpenHelper extends SQLiteOpenHelper {

  private final static String NAME = "search_record.db";
  private final static int DB_VERSION = 1;

  public RecordSQLiteOpenHelper(Context context) {
    super(context, NAME, null, DB_VERSION);
  }

  @Override public void onCreate(SQLiteDatabase db) {
    String sqlStr = "CREATE TABLE IF NOT EXISTS records (_id INTEGER PRIMARY KEY AUTOINCREMENT"
        + ", username TEXT, keyword TEXT, time NOT NULL DEFAULT (datetime('now','localtime')));";
    db.execSQL(sqlStr);
  }

  @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  }
}
