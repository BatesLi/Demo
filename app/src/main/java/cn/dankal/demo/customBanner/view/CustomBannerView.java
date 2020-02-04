package cn.dankal.demo.customBanner.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Scroller;

import cn.dankal.basic_lib.util.ToastUtil;
import cn.dankal.demo.R;

public class CustomBannerView extends ViewGroup {

    private Context mContext;
    private GestureDetector mGestureDetector;
    private Scroller mScroller;
    private int position;

    private int scrollX;
    private int startX;
    private int startY;

    int[] photos = {R.mipmap.item1, R.mipmap.item2, R.mipmap.item3, R.mipmap.item4, R.mipmap.item5,
            R.mipmap.item6};

    public CustomBannerView(Context context) {
        super(context);
        this.mContext = context;
        initChildView();
    }

    public CustomBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initChildView();
    }

    public CustomBannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initChildView();
    }

    public void initChildView() {
        for (int i = 0; i < photos.length; i++) {
            ImageView childImageView = new ImageView(getContext());
            childImageView.setBackgroundResource(photos[i]);
            this.addView(childImageView);
        }
        //创建一个手势识别器：这里主要就是靠 scrollBy()方法，来实现View跟随手的滑动而滑动。
        mGestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                //相对滑动距离，x滑动多少，view就跟着滑动多少。
                scrollBy((int) distanceX, 0);
                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        });
        mScroller = new Scroller(mContext);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        Log.e("TAG", "childNumber:  " + childCount);
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
        }
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childcound = getChildCount();
        for (int i = 0; i < childcound; i++) {
            View childView = getChildAt(i);
            childView.layout(i * getWidth(), t, (i + 1) * getWidth(), b);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ToastUtil.toToast("");
                break;
            case MotionEvent.ACTION_MOVE:
                //相对于初始位置滑动的距离
                scrollX = getScrollX();
                /*
                 * 你滑动的距离加上屏幕的一半，除以屏幕宽度，
                 * 就是当前图片显示的position.如果你滑动距离超过了屏幕的一半，这个position就加1
                 * */
                position = (getScrollX() + getWidth() / 2) / getWidth();
                //当你滑动到最后一张的时候，不能超出边界。
                if (position >= photos.length) {
                    position = photos.length - 1;
                }
                if (position < 0) {
                    position = 0;
                }
                if (mOnCustomBannerListener != null) {
                    Log.e("TAG", "offset=" + (float) (getScrollX() * 1.0 / ((1) * getWidth())));
                    mOnCustomBannerListener.OnPageScrollListener((float) (getScaleX() * 1.0 / ((1) * getWidth())), position);
                }
                break;
            //MotionEvent.ACTION_UP——最后一根手指离开屏幕的效果？当最后一根手指离开view，就开始进行滚动
            case MotionEvent.ACTION_UP:
                //scrollX——从初始化位置开始的相对滑动距离——既是开始滚动的start \ 偏移量的概念是什么？
                mScroller.startScroll(scrollX, 0, -(scrollX - position * getWidth()), 0);
                //Invalidate方法实现界面刷新,在ui线程中使用
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), 0);
            postInvalidate();
            if (mOnCustomBannerListener != null) {
                Log.e("TAG", "offset=" + (float) (getScrollX() * 1.0 / (getWidth())));
                mOnCustomBannerListener.OnPageScrollListener((float) (getScaleX() * 1.0 / (getWidth())), position);
            }
        }
    }
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
    /*
     position 当前所在页面
        positionOffset 当前所在页面偏移百分比
        positionOffsetPixels 当前所在页面偏移量
    * */

    public interface OnCustomBannerListener {
        void OnPageScrollListener(float positionOffset, int positionOffsetPixels);

        void OnPageScrollListener(int position);
    }

    public OnCustomBannerListener mOnCustomBannerListener;

    public void setOnCustomBannerListener(OnCustomBannerListener onCustomBannerListener) {
        this.mOnCustomBannerListener = onCustomBannerListener;
    }
}
