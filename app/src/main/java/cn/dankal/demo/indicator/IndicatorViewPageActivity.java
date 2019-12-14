package cn.dankal.demo.indicator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.dankal.basic_lib.util.ToastUtil;
import cn.dankal.demo.R;

/**
 * @author BatesLi
 * @date
 * @org
 * @email
 * @describe 使用viewPage来制作一个自定义的indicator
 */

public class IndicatorViewPageActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    public List<Fragment> mFragmentList;
    public IndicatorViewPageAdapter mIndicatorViewPageAdapter;
    @BindView(R.id.btn_one)
    Button mBtnOne;
    @BindView(R.id.btn_two)
    Button mBtnTwo;
    @BindView(R.id.btn_three)
    Button mBtnThree;
    @BindView(R.id.btn_four)
    Button mBtnFour;
    @BindView(R.id.btn_five)
    Button mBtnFive;
    @BindView(R.id.img_cursor)
    ImageView mImgCursor;
    @BindView(R.id.linear_cursor)
    LinearLayout mLinearCursor;
    @BindView(R.id.view_page_indicator)
    ViewPager mViewPageIndicator;
    Button[] mButtons;
    //定义获取所有按钮的宽度数组
    private int[] mWidrhArgs;
    private int mCursorX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator);
        ButterKnife.bind(this);

        mButtons = new Button[]{mBtnOne, mBtnTwo, mBtnThree, mBtnFour, mBtnFive};
        mImgCursor.setBackgroundColor(Color.RED);

        initViewPage();
        initBtnColor();
    }

    public void initBtnColor() {
        mBtnOne.setTextColor(Color.BLACK);
        mBtnTwo.setTextColor(Color.BLACK);
        mBtnThree.setTextColor(Color.BLACK);
        mBtnFour.setTextColor(Color.BLACK);
        mBtnFive.setTextColor(Color.BLACK);

    }

    public void initViewPage() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new IndicatorFragmentOne());
        mFragmentList.add(new IndicatorFragmentOne());
        mFragmentList.add(new IndicatorFragmentOne());
        mFragmentList.add(new IndicatorFragmentOne());
        mFragmentList.add(new IndicatorFragmentOne());

        mViewPageIndicator.setAdapter(new IndicatorViewPageAdapter(getSupportFragmentManager()
                , mFragmentList));
        mViewPageIndicator.setOnPageChangeListener(this);
        mViewPageIndicator.setCurrentItem(0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        if (mWidrhArgs == null) {
            mWidrhArgs = new int[]{mBtnOne.getWidth(), mBtnTwo.getWidth(),
                    mBtnThree.getWidth(), mBtnFour.getWidth(), mBtnFive.getWidth()};
        }

        initBtnColor();
        mButtons[position].setTextColor(Color.RED);
        cursorAnim(position);
        ToastUtil.toToast("position是你当前选中的页面的Position位置编号" + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void cursorAnim(int curItem) {
        mCursorX = 0;
        //总体:指示器的宽度 == 文字的宽度
        //指示器总宽度:把每一个按钮的宽度是为 一个数组的元素，放入数组中。
       /* LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)mImgCursor.getLayoutParams();
        //获取每一个按钮的文字的宽度
        params.weight = mWidrhArgs[curItem] - mButtons[0].getPaddingLeft() * 2;
        mImgCursor.setLayoutParams(params);*/

        //循环取出buttons数组的宽度
        for (int i = 0; i < curItem; i++) {
            mCursorX = mCursorX + mButtons[i].getWidth();
        }
        mImgCursor.setX(mCursorX + mButtons[curItem].getPaddingLeft());
    }
}
