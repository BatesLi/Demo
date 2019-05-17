package cn.dankal.demo.Read.utils;

import android.view.View;
import android.view.ViewGroup;
import com.blankj.utilcode.utils.Utils;

public class DensityUtils {

  /**
   *
   * @return 根据手机的分辨率从dp的单位转为px(像素)
   */
  public static int dip2px(float dpValue) {
   /*这个得到的不应该叫做密度，应该是密度的一个比例。不是真实的屏幕密度，而是相对于某个值的屏幕密度。
    也可以说是相对密度*/
    final float scale = Utils.getContext().getResources().getDisplayMetrics().density;
    return (int)(dpValue * scale + 0.5f);
  }

  /**
   * @param pxValue
   * @return 手机分辨率从px转为化dip
   */
  public static int px2dip(float pxValue) {
    final float scale = Utils.getContext().getResources().getDisplayMetrics().density;
    return (int)(pxValue / scale + 0.5f);
  }
  public static ViewGroup.LayoutParams setViewMargin(View view,boolean isDp,int left,
      int right,int top,int bottom) {
    if (view == null) {
        return null;
    }
    int LeftPx = left;
    int RightPx = right;
    int TopPx = top;
    int BottomPx = bottom;

    ViewGroup.LayoutParams params = view.getLayoutParams();
    ViewGroup.MarginLayoutParams marginLayoutParams = null;

    //获取View的margin设置参数
    if (params instanceof ViewGroup.MarginLayoutParams) {
        marginLayoutParams = (ViewGroup.MarginLayoutParams)params;
    }else {
        //不存在的时候，创建一个参数
        marginLayoutParams = new ViewGroup.MarginLayoutParams(params);
    }
    //根据Dp与Px转化值进行计算
    if (isDp) {
     LeftPx = dip2px(left);
     RightPx = dip2px(right);
     TopPx = dip2px(top);
     BottomPx = dip2px(bottom);
    }
    //设置margin
    marginLayoutParams.setMargins(left,right,top,bottom);
    view.setLayoutParams(marginLayoutParams);
    view.requestLayout();
    return marginLayoutParams;
  }
}
