package cn.dankal.demo.SearchPractise.MVP.GitHub;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cn.dankal.demo.R;
import cn.dankal.demo.SearchPractise.MVP.GitHub.Util.DisplayUtil;

public class LoadingCircle extends View {

    private int mRadius;
    //The next dot is selected
    private int mIndex = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            ++mIndex;
            if (mIndex == 5) {
                mIndex = 0;
            }
            postInvalidate();
        }
    };

    public LoadingCircle(Context context) {
        super(context);
    }

    public LoadingCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams(context);
    }

    private void initParams(Context context) {
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRadius = DisplayUtil.dp2px(getContext(), 10);
        ++mIndex;
        //创建圆形
        Paint paintBlack = new Paint();
        paintBlack.setColor(getResources().getColor(R.color.black));

        Paint paintGreen = new Paint();
        paintGreen.setColor(getResources().getColor(R.color.card_3));

        Paint paintRed = new Paint();
        paintRed.setColor(getResources().getColor(R.color.refresh_progress_3));

        for (int i = 0; i < 5; i++) {
            canvas.drawCircle(getHeight() / 2 + mRadius * i * 2 + 5 * i, getHeight() / 2, mRadius, paintBlack);
        }
        //创建的圆点 0 的position对不上第一次创建圆点的 0 的位置。既是：两者间隔跳转一个圆点的位置来进行 循环。位置对不上
        canvas.drawCircle(getHeight() / 2 + mRadius * mIndex * 2 + 5 * mIndex, getHeight() / 2, mRadius, paintRed);
        mHandler.sendEmptyMessageDelayed(0, 1000);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeMessages(0);
        mHandler = null;
    }
}
