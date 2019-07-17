package cn.dankal.demo.SearchPractise.SearchWanAndroid.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import cn.dankal.demo.R;

@SuppressLint("AppCompatCustomView")
public class ClearEditText extends EditText
    implements View.OnTouchListener, View.OnFocusChangeListener, TextWatcher {

  private Drawable mClearIcon;
  private OnTouchListener mOnTouchListener;
  private OnFocusChangeListener mOnFocusChangeListener;

  public ClearEditText(Context context) {
    super(context);
    initialize(context);
  }

  public ClearEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
    initialize(context);
  }

  public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initialize(context);
  }

  @Override
  public void setOnTouchListener(OnTouchListener mOnTouchListener) {
    this.mOnTouchListener = mOnTouchListener;
  }

  @Override
  public void setOnFocusChangeListener(OnFocusChangeListener mOnFocusChangeListener) {
    this.mOnFocusChangeListener = mOnFocusChangeListener;
  }

  private void initialize(final Context context) {
    final Drawable drawable = ContextCompat.getDrawable(context, R.mipmap.clear_text);
    //使用wrap函数获取新的Drawable | 如果两个ImageView共享了同样的资源,mutate()使用它就可以让另一个不受影响。
    final Drawable wrappedDrawable = DrawableCompat.wrap(drawable).mutate();
    mClearIcon = wrappedDrawable;
    mClearIcon.setBounds(0, 0, mClearIcon.getIntrinsicWidth(), mClearIcon.getIntrinsicHeight());
    setClearIconVisible(false);
    super.setOnTouchListener(this);
    super.setOnFocusChangeListener(this);
    super.addTextChangedListener(this);
    ViewCompat.setBackgroundTintList(this,
        ContextCompat.getColorStateList(context, R.color.light_black));
  }

  private void setClearIconVisible(final boolean visible) {
    if (mClearIcon.isVisible() == visible) return;
    mClearIcon.setVisible(visible, false);
    final Drawable[] compoundDrawable = getCompoundDrawables();
    setCompoundDrawables(
        compoundDrawable[0],
        compoundDrawable[1],
        visible ? mClearIcon : null, compoundDrawable[3]
    );
  }

  @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

  }

  @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
    if (isFocused()) {
      setClearIconVisible(s.length() > 0);
    }
  }

  @Override public void afterTextChanged(Editable s) {

  }

  @Override public void onFocusChange(View v, boolean hasFocus) {
    if (hasFocus && getText() != null) {
      setClearIconVisible(getText().length() > 0);
    } else {
      setClearIconVisible(false);
    }
    if (mOnFocusChangeListener != null) {
      mOnFocusChangeListener.onFocusChange(v, hasFocus);
    }
  }

  @Override public boolean onTouch(View v, MotionEvent event) {
    /*
     * getX()是表示Widget相对于自身左上角的x坐标,而getRawX()是表示相对于屏幕左上角的x坐标值
     * (注意:这个屏幕左上角是手机屏幕左上角,不管activity是否有titleBar或是否全屏幕)
     * ,getY(),getRawY()一样的道理
     * */
    final int x = (int) event.getX();
    if (mClearIcon.isVisible()
        && x > getWidth() - getPaddingRight() - mClearIcon.getIntrinsicWidth()) {
      if (event.getAction() == MotionEvent.ACTION_UP) {
        setText("");
      }
      return true;
    }
    return mOnTouchListener != null && mOnTouchListener.onTouch(v, event);
  }
}
