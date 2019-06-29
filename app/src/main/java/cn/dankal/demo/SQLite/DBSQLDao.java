package cn.dankal.demo.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DBSQLDao {

  private static final String TAG = "skylark--->DBSQLDao";
  private final String[] COLUMNS = new String[] {"_id", "Name", "Gender", "City"};
  private Context mContext;
  private DBSQLiteHelper mDbsqLiteHelper;

  public DBSQLDao(Context context) {
    this.mContext = context;
    mDbsqLiteHelper = new DBSQLiteHelper(context);
  }

  /**
   * 初始化数据
   */
  public void initTable() {
    SQLiteDatabase sqLiteDatabase = null;
    try {
      sqLiteDatabase = mDbsqLiteHelper.getWritableDatabase();
      sqLiteDatabase.beginTransaction();
      sqLiteDatabase.execSQL("insert into "
          + DBSQLiteHelper.TABLE_NAME
          + "(_id,Name,Gender,City) values (1,'张三','男','湖北')");
      sqLiteDatabase.execSQL("insert into "
          + DBSQLiteHelper.TABLE_NAME
          + " (_id,Name,Gender,City) values (2,'李四','男','湖南')");
      sqLiteDatabase.execSQL("insert into "
          + DBSQLiteHelper.TABLE_NAME
          + " (_id,Name,Gender,City) values (3,'王二','女','江西')");
      sqLiteDatabase.execSQL("insert into "
          + DBSQLiteHelper.TABLE_NAME
          + " (_id,Name,Gender,City) values (4,'赵五','男','河南')");
      sqLiteDatabase.execSQL("insert into "
          + DBSQLiteHelper.TABLE_NAME
          + " (_id,Name,Gender,City) values (5,'钱六','女','河北')");
      sqLiteDatabase.setTransactionSuccessful();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (sqLiteDatabase != null) {
        sqLiteDatabase.endTransaction();
        sqLiteDatabase.close();
      }
    }
  }

  /**
   * 获取全部的数据
   */
  public List<DBSQLBean> getAllDaoData() {
    SQLiteDatabase sqLiteDatabase = null;
    Cursor cursor = null;
    try {
      sqLiteDatabase = mDbsqLiteHelper.getWritableDatabase();
      cursor = sqLiteDatabase.query(DBSQLiteHelper.TABLE_NAME, COLUMNS, null,
          null, null, null, null);
      if (cursor.getCount() > 0) {
        List<DBSQLBean> dbsqlBeanList = new ArrayList<>();
        while (cursor.moveToNext()) {
          dbsqlBeanList.add(parseBean(cursor));
        }
        return dbsqlBeanList;
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (cursor != null) {
        cursor.close();
      }
      if (sqLiteDatabase != null) {
        mDbsqLiteHelper.close();
      }
    }
    return null;
  }

  /**
   * 获取 cursor里面的数据
   */
  private DBSQLBean parseBean(Cursor cursor) {
    DBSQLBean dbsqlBean = new DBSQLBean();
    dbsqlBean.id = cursor.getInt(cursor.getColumnIndex("_id"));
    dbsqlBean.name = cursor.getString(cursor.getColumnIndex("Name"));
    dbsqlBean.gender = cursor.getString(cursor.getColumnIndex("Gender"));
    dbsqlBean.city = cursor.getString(cursor.getColumnIndex("City"));
    return dbsqlBean;
  }

  /**
   * 插入数据
   */
  public boolean insertData() {
    SQLiteDatabase db = null;
    try {
      db = mDbsqLiteHelper.getWritableDatabase();
      db.beginTransaction();
      ContentValues contentValues = new ContentValues();
      contentValues.put("Name", "张三");
      contentValues.put("Gender", "男");
      contentValues.put("City", "湖北");
      db.insertOrThrow(DBSQLiteHelper.TABLE_NAME, null, contentValues);
      db.setTransactionSuccessful();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (db != null) {
        db.endTransaction();
        db.close();
      }
    }
    return false;
  }

  /**
   * 删除数据
   */
  public boolean deleteData() {
    SQLiteDatabase db = null;
    try {
      db = mDbsqLiteHelper.getWritableDatabase();
      db.beginTransaction();
      db.delete(DBSQLiteHelper.TABLE_NAME, "Name=?", new String[] {"张三"});
      db.setTransactionSuccessful();
      return true;
    } catch (Exception e) {
      Log.e(TAG, "deleteData: ", e);
    } finally {
      if (db != null) {
        db.endTransaction();
        db.close();
      }
    }
    return false;
  }

  /**
   * 更新数据
   */
  public boolean upData() {
    SQLiteDatabase db = null;
    String name = "改变后的值";
    try {
      db = mDbsqLiteHelper.getWritableDatabase();
      db.beginTransaction();
      ContentValues contentValues = new ContentValues();
      contentValues.put("name", name);
      db.update(DBSQLiteHelper.TABLE_NAME, contentValues, "name=?", new String[] {"张三"});
      db.setTransactionSuccessful();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (db != null) {
        db.endTransaction();
        db.close();
      }
    }
    return false;
  }
}





