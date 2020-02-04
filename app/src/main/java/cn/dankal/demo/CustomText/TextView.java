package cn.dankal.demo.CustomText;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import cn.dankal.demo.R;

public class TextView extends View {

    Paint mPaint;
    private String mText;
    private int mTextSize;
    private int mTextColor;

    public TextView(Context context) {
        this(context, null);
    }

    public TextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextView);
        mText = typedArray.getString(R.styleable.TextView_lctyText);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.TextView_lctySize, sp2px(20));
        mTextColor = typedArray.getColor(R.styleable.TextView_lctyColor, mTextColor);
        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
    }

    private int sp2px(int size) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, size, getResources().getDisplayMetrics());
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //得到宽高的值
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST) {
            Rect rect = new Rect();
            mPaint.getTextBounds(mText, 0, mText.length(), rect);
            width = getPaddingLeft() + getPaddingRight() + rect.width();
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            Rect rect = new Rect();
            mPaint.getTextBounds(mText, 0, mText.length(), rect);
            height = getPaddingBottom() + getPaddingTop() + rect.height();
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint.FontMetricsInt metricsInt = mPaint.getFontMetricsInt();
        int dy = (metricsInt.bottom - metricsInt.top) / 2 - metricsInt.bottom;
        int y = getHeight() / 2 + dy;
        canvas.drawText(mText, getPaddingLeft(), y, mPaint);
    }
}
