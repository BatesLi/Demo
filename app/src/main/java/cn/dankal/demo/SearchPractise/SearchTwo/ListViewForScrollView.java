package cn.dankal.demo.SearchPractise.SearchTwo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @author
 * @date
 * @org
 * @email
 * @describe 一个可以兼容scrollView的listView
 */

public class ListViewForScrollView extends ListView {
  public ListViewForScrollView(Context context) {
    super(context);
  }

  public ListViewForScrollView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ListViewForScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
    super.onMeasure(widthMeasureSpec, expandSpec);
  }
}
