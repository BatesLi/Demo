package cn.dankal.demo.Read.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import com.blankj.utilcode.utils.Utils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CommonUtils {

  /**
   * @return 随机颜色
   */
  public static int randomColor() {
    Random random = new Random();
    int rad = random.nextInt(150) + 50;
    int green = random.nextInt(150) + 50;
    int blue = random.nextInt(150) + 50;
    return Color.rgb(rad,green,blue);
  }

  /**
   * @return 获取年日月中的日
   */
  private String getDate() {
    Date date = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("day");
    return simpleDateFormat.format(date);
  }

  /**
   * @param context
   * @return 分辨率
   */
  public static int getScreenWidthPixels(Context context) {
    DisplayMetrics displayMetrics = new DisplayMetrics();
    ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
        .getMetrics(displayMetrics);
    return displayMetrics.widthPixels;
  }
  public static Resources getResources() {
    return Utils.getContext().getResources();
  }

  public static float getDimens(int resId) {
    return getResources().getDimension(resId);
  }
  public static Drawable getDrawable(int resId) {
    return getResources().getDrawable(resId);
  }
  public static int getColor(int resId) {
    return getResources().getColor(resId);
  }
  public static String[] getStringArrary(int resId) {
    return getResources().getStringArray(resId);
  }
  public static void romeSelfFromeParent(View child) {
    if (child != null) {
      ViewParent viewParent = child.getParent();

      if (viewParent instanceof ViewGroup) {
          ViewGroup viewGroup = (ViewGroup)viewParent;
          viewGroup.removeView(child);
      }
    }
  }
}
