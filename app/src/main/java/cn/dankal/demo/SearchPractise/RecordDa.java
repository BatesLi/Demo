package cn.dankal.demo.SearchPractise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/*
 * 历史操作类
 * */
public class RecordDa {

  private final String TABLE_NAME = "records";
  private SQLiteDatabase sqLiteDatabase;
  private RecordSQLiteOpenHelper recordSQLiteOpenHelper;
  private String mUsername;
  private NotifyDataChanged notifyDataChanged;

  public void setNotifyDataChanged(
      NotifyDataChanged notifyDataChanged) {
    this.notifyDataChanged = notifyDataChanged;
  }

  /**
   * 添加历史记录
   *
   * @param records 记录
   */
  public void addRecords(String records) {
    //如果这条记录没有则添加，有则更新时间
    int recordId = getRecordId(records);
    try {
      sqLiteDatabase = getReadableDatabase();
      if (-1 == recordId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", mUsername);
        contentValues.put("keyword", records);
        //对数据库添加搜索记录
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
      } else {
        /*Date date = new Date();
        @SuppressLint("SimpleDataFormat")SimpleDateFormat simpleDateFormat
             = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //更新搜索历史数据时间
        ContentValues contentValues = new ContentValues();
        contentValues.put("time",simpleDateFormat.format(date));
        sqLiteDatabase.update(TABLE_NAME,contentValues,"_id = ?"
        ,new String[]{Integer.toString(recordId)});*/
      }
      if (notifyDataChanged != null) {
        notifyDataChanged.notifyDataChanged();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public RecordDa(Context context, String username) {
    recordSQLiteOpenHelper = new RecordSQLiteOpenHelper(context);
    mUsername = username;
  }

  private synchronized SQLiteDatabase getReadableDatabase() {
    return recordSQLiteOpenHelper.getReadableDatabase();
  }

  private synchronized SQLiteDatabase getWritableDatabase() {
    return recordSQLiteOpenHelper.getWritableDatabase();
  }

  /**
   * 关闭数据库
   */
  public void closeDatabase() {
    if (sqLiteDatabase != null) {
      sqLiteDatabase.close();
    }
  }

  /**
   * 获取指定数量的搜索记录
   */
  public List<String> getRecordByNumber(int recordNumber) {
    List<String> recordList = new ArrayList<>();
    if (recordNumber < 0) {
      throw new IllegalArgumentException();
    } else if (0 == recordNumber) {
      return recordList;
    } else {
      Cursor cursor = null;
      try {
        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.query(TABLE_NAME, null, "username = ?"
            , new String[] {mUsername}, null, null
            , "time desc limit " + recordNumber);
        while (cursor.moveToNext()) {
          String name = cursor.getString(cursor.getColumnIndexOrThrow("keyword"));
          recordList.add(name);
        }
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      } finally {
        if (cursor == null) {
          cursor.close();
        }
      }
    }
    return recordList;
  }

  /**
   * 清除用户指定的记录
   */
  public void deleteUsernameAllRecord() {
    try {
      sqLiteDatabase = getWritableDatabase();
      sqLiteDatabase.delete(TABLE_NAME, "username = ?", new String[] {mUsername});
      if (notifyDataChanged != null) {
        notifyDataChanged.notifyDataChanged();
      }
    } catch (SQLException s) {
      s.printStackTrace();
      Log.e(TABLE_NAME, "清除所有历史记录失败");
    } finally {
    }
  }

  /**
   * 判断是否含有该搜索记录
   */
  public int getRecordId(String record) {
    int isHanRecord = -1;
    Cursor cursor = null;
    try {
      sqLiteDatabase = getReadableDatabase();
      cursor = sqLiteDatabase.query(TABLE_NAME, null, "username = ?"
          , new String[] {mUsername}, null, null, null);
      while (cursor.moveToNext()) {
        if (record.equals(cursor.getString(cursor.getColumnIndexOrThrow("keyword")))) {
          isHanRecord = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        }
      }
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } finally {
      if (cursor != null) {
        cursor.close();
      }
    }
    return isHanRecord;
  }

  /**
   * 移除数据变化监听
   */
  public void removeNotifyDatabaseChanged() {
    if (notifyDataChanged != null) {
      notifyDataChanged = null;
    }
  }

  /**
   * 数据监听接口
   */
  public interface NotifyDataChanged {
    void notifyDataChanged();
  }
}
