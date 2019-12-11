package cn.dankal.demo.SearchPractise.MVP.GitHub.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cn.dankal.demo.R;

public class IndicateDotView extends View {

    private int mUnSelectColor;
    private int mSelectColor;
    private int mDivideColor;
    private int mDivideWidth;
    private boolean mIsLeastOne = true;
    private int mMargin;
    private int mRadius;
    private boolean mClickable;
    private int mMaxNumber;
    private int mNumber;
    private Paint mPaint;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            ++mNumber;
            if (mNumber == 5) {
                mNumber = 0;
            }
            postInvalidate();
        }
    };

    public IndicateDotView(Context context) {
        super(context);
    }

    public IndicateDotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public IndicateDotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IndicateDotView);
        mUnSelectColor = typedArray.getColor(R.styleable.IndicateDotView_indicate_unselect_color, mUnSelectColor);
        mSelectColor = typedArray.getColor(R.styleable.IndicateDotView_indicate_select_color, mSelectColor);
        mDivideWidth = (int) typedArray.getDimension(R.styleable.IndicateDotView_indicate_divide_width, mDivideWidth);
        mDivideColor = typedArray.getColor(R.styleable.IndicateDotView_indicate_divide_color, mDivideColor);
        mIsLeastOne = typedArray.getBoolean(R.styleable.IndicateDotView_indicate_is_least_one, mIsLeastOne);
        mMargin = (int) typedArray.getDimension(R.styleable.IndicateDotView_indicate_margin, mMargin);
        mRadius = (int) typedArray.getDimension(R.styleable.IndicateDotView_indicate_radius, mRadius);
        mClickable = typedArray.getBoolean(R.styleable.IndicateDotView_android_clickable, mClickable);
        mMaxNumber = typedArray.getInt(R.styleable.IndicateDotView_indicate_max_number, mMaxNumber);
        mNumber = typedArray.getInt(R.styleable.IndicateDotView_indicate_number, mNumber);
        //回收TypeAray
        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < 5; i++) {
            //mNuber始终比 i大一位
            if (i <= mNumber) {
                mPaint = new Paint();
                mPaint.setColor(getResources().getColor(R.color.colorAccent));
                drawCircle(canvas, i);
            } else {
                mPaint = new Paint();
                mPaint.setColor(getResources().getColor(R.color.black));
                drawCircle(canvas, i);
            }
        }
        mHandler.sendEmptyMessageDelayed(0, 1000);
    }

    private void drawCircle(Canvas canvas, int i) {
        canvas.drawCircle(getHeight() / 4 + 20 * i + i * 30, getHeight() / 2, 20, mPaint);
    }
}
