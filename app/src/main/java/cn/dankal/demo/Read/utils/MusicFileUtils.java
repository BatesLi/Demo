package cn.dankal.demo.Read.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import com.blankj.utilcode.utils.FileUtils;
import java.io.File;
import java.util.ArrayList;

public class MusicFileUtils {

  /**根据本地歌名查看音乐
   * @param context
   * @param key
   * @return
   */
  //不需要关心如何去扫描手机中的文件，只要了解如何查询和使用这些信息就可以了。

  public static String[] queryMusic(Context context,String key) {
    ArrayList<String> nameList = new ArrayList<>();
    Cursor cursor = null;
    //ContentProvider作为安卓四大组件之一,获取内容解析器，并查询数据库
    try {
      cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null,
          MediaStore.Audio.Media.DISPLAY_NAME + " LIKE '%" + key + "%'",
          null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

      while (cursor.moveToNext()) {
          String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));//音乐路径
        //这样的判断条件,path有值。返回true。意思是不为空(path),继续执行下面的句子。
          if (!MusicFileUtils.isExists(path)) {
              continue;
        }
          String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));//歌曲名
          nameList.add(name);
      }
    }catch (Exception e) {
      e.printStackTrace();
    }finally {
      if (cursor != null) {
          cursor.close();
      }
    }
    if (nameList.isEmpty()) {
        return new String[]{};
    }
    return (String[])nameList.toArray(new String[nameList.size()]);
  }
  /*
  * public boolean exists()测试此抽象路径名表示的文件或目录是否存在.
返回:当且仅当此抽象路径名表示的文件或目录存在时,返回true;否则返回false;
  * */
  public static boolean isExists(String path) {
    File file = new File(path);
    return file.exists();
  }
}









