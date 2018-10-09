package cn.dankal.demo.mvp.Decoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import cn.dankal.demo.R;

public class MarginDecoration extends RecyclerView.ItemDecoration {
  /*ItemDecoration是一个抽象类,就是用来装饰RecyclerView的子item的，
  功能并不仅仅是添加间距绘制分割线*/
  private int margin;

  public MarginDecoration(Context context) {
    margin = context.getResources().getDimensionPixelSize(R.dimen.item_margin);
  }

  //getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) 设置四边边距
  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent
      , RecyclerView.State state) {
    outRect.set(margin, margin, margin, margin);
  }
}







