package cn.dankal.demo.customBanner.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
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
        super(context);
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
                ToastUtil.toToast("移动");
                break;
            case MotionEvent.ACTION_MOVE:
                scrollX = getScrollX();
                /*
                 * 你滑动的距离加上屏幕的一半，除以屏幕宽度，
                 * 就是当前图片显示的pos.如果你滑动距离超过了屏幕的一半，这个pos就加1
                 * */
                position = (getScrollX() + getWidth() / 2) / getWidth();
                //当你滑动到最后一张的时候，不能超出边界。
                if (position >= photos.length) {
                    position = photos.length - 1;
                }
                if (position < 0) {
                    position = 0;
                }
                break;
            case MotionEvent.ACTION_UP:
                scrollTo(position * getWidth(), 0);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
