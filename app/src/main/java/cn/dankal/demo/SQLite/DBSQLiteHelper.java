package cn.dankal.demo.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBSQLiteHelper extends SQLiteOpenHelper {

  public static final String TABLE_NAME = "Mytable";
  public static final String TABLE_NAME_1 = "Book";
  public static final String TABLE_NAME_2 = "Category";
  public static final String CREATE_MYTABLE_TABLE =
      "create table if not exists " + TABLE_NAME + "("
          + "_id integer primary key,"
          + "Name text,"
          + "Gender text,"
          + "City text)";
  public static final String CREATE_BOOK_TABLE = "create table if not exists " + TABLE_NAME_1 + "("
      + "_id integer primary key,"
      + "author text,"
      + "price real,"
      + "pages integer,"
      + "name text,"
      + "category_id integer)";
  public static final String CREATE_CATEGORY_TABLE =
      "create table if not exists " + TABLE_NAME_2 + "("
          + "_id integer primary key autoincrement,"
          + "category_name text,"
          + "category_code integer)";
  private static final String DB_NAME = "database.db";
  private static final int DB_VERSION = 1;

  public DBSQLiteHelper(Context context) {
    super(context, DB_NAME, null, DB_VERSION);
  }

  @Override public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(CREATE_MYTABLE_TABLE);
    sqLiteDatabase.execSQL(CREATE_BOOK_TABLE);
    sqLiteDatabase.execSQL(CREATE_CATEGORY_TABLE);
  }

  @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    switch (oldVersion) {
      case 1:
        db.execSQL(CREATE_BOOK_TABLE);
        db.execSQL(CREATE_CATEGORY_TABLE);
      case 2:
        db.execSQL("alter table BOOK add column category_id integer");
      default:
    }
  }
}