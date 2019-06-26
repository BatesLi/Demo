package cn.dankal.demo.SearchPractise.SearchTwo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class SearchSQLiteHelp extends SQLiteOpenHelper {

  private String CREATE_SEARCH = "create table table_search(_id integer primary key autoincrement"
      + ",username varchar(200),password varchar(200))";

  public SearchSQLiteHelp(@Nullable Context context) {
    super(context, "search_db", null, 1);
  }

  @Override public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_SEARCH);
  }

  @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  }
}


