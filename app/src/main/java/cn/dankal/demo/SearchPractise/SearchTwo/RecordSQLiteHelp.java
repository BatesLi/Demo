package cn.dankal.demo.SearchPractise.SearchTwo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class RecordSQLiteHelp extends SQLiteOpenHelper {

  private String CREATE_RECORDS_TABLE =
      "create table table_records(_id integer primary key autoincrement"
          + ",username varchar(200),password varchar(200))";

  public RecordSQLiteHelp(@Nullable Context context) {
    super(context, "records_db", null, 1);
  }

  @Override public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_RECORDS_TABLE);
  }

  @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  }
}
