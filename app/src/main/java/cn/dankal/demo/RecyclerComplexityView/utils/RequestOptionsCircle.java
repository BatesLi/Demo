package cn.dankal.demo.RecyclerComplexityView.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import java.security.MessageDigest;

public class RequestOptionsCircle extends BitmapTransformation {

  //裁剪图片为圆

  @Override
  protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth,
      int outHeight) {
    return CircleCrop(pool, toTransform);
  }

  private Bitmap CircleCrop(BitmapPool pool, Bitmap toTransform) {
    if (toTransform == null) return null;
    //Math.min 求两数中最小(得到图片最小边)
    int size = Math.min(toTransform.getWidth(), toTransform.getHeight());
    //计算图片起点
    int x = (toTransform.getWidth() - size) / 2;
    int y = (toTransform.getHeight() - size) / 2;

    /*根据参数创建新位图
    x int         子位图第一个像素在源位图的X坐标
    y int		  子位图第一个像素在源位图的y坐标
    width int     子位图每一行的像素个数
    height int    子位图的行数*/
    //创建新的bitmap
    Bitmap squared = Bitmap.createBitmap(toTransform, x, y, size, size);
    //得到glide中BitmapPool的bitmap位图对象(ARGB_8888——代表32位ARGB位图 )
    Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
    if (result == null) {
      result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
    }
    //canvas（Android系统的画布）
    Canvas canvas = new Canvas(result);
    //paint这个类可以画几何图形，文本和bitmap
    Paint paint = new Paint();
    /* shader是着色器,BitmapShader(位图着色器)是唯一个可以用来给一个图片着色，其他四个就是渐变、渲染效果
     *  bitmapShader()方法，一个Bitmap外，还需要两个TileMode枚举类型的参数，一个代表在x轴的模式，一个在y轴的模式
     *  TileMode(瓷砖模式)是Shader中的一个枚举，有三个值，其中使用CLAMP拉伸模式(常用)得到一个圆形图片
     * */
    paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP
        , BitmapShader.TileMode.CLAMP));
    //该方法作用是抗锯齿
    paint.setAntiAlias(true);
    float r = size / 2f;
    //cx：圆心的x坐标。cy：圆心的y坐标。radius：圆的半径。绘制时所使用的画笔
    canvas.drawCircle(r, r, r, paint);
    return result;
  }

  @Override
  public void updateDiskCacheKey(MessageDigest messageDigest) {

  }
}
