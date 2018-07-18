package cn.dankal.basic_lib.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import butterknife.ButterKnife;
import cn.dankal.basic_lib.R;
import cn.dankal.basic_lib.util.TitleBarUtils;
import cn.dankal.basic_lib.util.ToastUtil;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, BaseView {

    private static final String TAG = "BaseActivity";
    protected Toolbar toolbar;
    protected LinearLayout toolbarContainer;

    protected QMUITipDialog loadingDialog;
    private CompositeDisposable mDisposables = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (contentViewLayoutResId() != 0) {
            setContentView(contentViewLayoutResId());

            //ButterKnife绑定
            ButterKnife.bind(this);

            //初始化组件
            initComponents();
        }

        //一般用于加载网络请求
        obtainData();
    }


    /**
     * 设置沉浸式状态栏和标题栏背景
     *
     * @param isStatusBarTranslucent 是否设置沉浸式状态栏
     * @param isStatusBarTextBlack   设置状态栏字体、图标是否为黑色
     * @param toolBarBackgroundResId 设置标题栏背景,输入 0 直接跳过
     */
    public void setStatusBarAndToolBar(boolean isStatusBarTranslucent, boolean isStatusBarTextBlack,
                                       int toolBarBackgroundResId) {
        //需先设置标题栏背景再设置沉浸式，若没有标题栏或资源id则跳过
        setToolBarBackgroundResId(toolBarBackgroundResId);

        //是否设置沉浸式状态栏
        if (isStatusBarTranslucent) {
            QMUIStatusBarHelper.translucent(this);
        }

        //设置状态栏字体、图标是否为黑色
        if (isStatusBarTextBlack) {
            QMUIStatusBarHelper.setStatusBarLightMode(this);
        } else {
            QMUIStatusBarHelper.setStatusBarDarkMode(this);
        }

    }

    /**
     * 设置标题栏背景
     *
     * @param toolBarBackgroundResId 背景资源，可以是Color或者Drawable
     */
    public void setToolBarBackgroundResId(int toolBarBackgroundResId) {
        final String resTypeColor = "color";
        final String resTypeDrawable = "drawable";

        if (toolBarBackgroundResId != 0 && toolbar != null) {
            try {
                //获取资源Id的类别
                String resType = getResources().getResourceTypeName(toolBarBackgroundResId);
                //color,drawable
                Log.i(TAG, "setStatusBarAndToolBar: " + resType);

                if (resTypeColor.equals(resType)) {
                    //资源类型为Color
                    int bgColor = getResources().getColor(toolBarBackgroundResId);
                    toolbarContainer.setBackgroundColor(bgColor);
                } else if (resTypeDrawable.equals(resType)) {
                    //资源类型为Drawable
                    toolbarContainer.setBackgroundResource(toolBarBackgroundResId);
                }
            } catch (Exception e) {
            }
        }
    }


    /**
     * setContentView(LayoutResId)
     *
     * @return LayoutResId
     */
    @LayoutRes
    protected abstract int contentViewLayoutResId();

    /**
     * 初始化组件
     */
    protected abstract void initComponents();


    /**
     * 一般用于加载网络请求
     * 此方法不是抽象方法，通过覆盖实现，可调用多次
     */
    public void obtainData() {
    }

    /**
     * 添加 只包含标题文本的标题栏
     *
     * @param title 标题文本
     */
    public void addSingleTitleBar(@NonNull String title) {
        addMenuTitleBar(title, 0, null);
    }

    /**
     * 添加 包含标题文本、系统Menu 的标题栏
     *
     * @param title                   标题文本
     * @param menuId                  MenuId
     * @param onMenuItemClickListener Menu点击监听
     */
    public void addMenuTitleBar(@NonNull String title, int menuId,
                                Toolbar.OnMenuItemClickListener onMenuItemClickListener) {
        toolbarContainer = (LinearLayout) TitleBarUtils.init(this, R.layout.layout_toolbar);
        toolbar = toolbarContainer.findViewById(R.id.toolbar);
        toolbar.findViewById(R.id.iv_onback).setOnClickListener(this);

        //标题
        ((TextView) toolbar.findViewById(R.id.tv_title)).setText(title);

        //菜单
        if (onMenuItemClickListener != null && menuId != 0) {
            toolbar.inflateMenu(menuId);
            toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        }
    }


    /**
     * 添加 包含标题文本、自定义Menu 的标题栏
     * <p>
     * 若Menu为纯文本，请使用addMenuTitleBar();
     * 若Menu icon 为系统icon 或 Vector Drawable，请使用addMenuTitleBar();
     *
     * @param title               标题文本
     * @param menuImgResId        图片资源
     * @param onClickMenuListener Menu点击监听
     */
    public void addCustomMenuTitleBar(@NonNull String title, int menuImgResId,
                                      View.OnClickListener onClickMenuListener) {
        LinearLayout linearLayout = (LinearLayout) TitleBarUtils.init(this, R.layout.layout_toolbar);
        toolbar = linearLayout.findViewById(R.id.toolbar);
        toolbar.findViewById(R.id.iv_onback).setOnClickListener(this);

        //标题
        ((TextView) toolbar.findViewById(R.id.tv_title)).setText(title);

        //自定义菜单
        try {
            if (menuImgResId != 0 && onClickMenuListener != null) {
                ImageView menuImageView = toolbar.findViewById(R.id.iv_custom_menu);
                menuImageView.setImageResource(menuImgResId);
                menuImageView.setOnClickListener(onClickMenuListener);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 显示等待进度条
     */
    @Override
    public void showLoadingDialog() {
        loadingDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();
        loadingDialog.show();
    }

    /**
     * 销毁等待进度条
     */
    @Override
    public void dismissLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    /**
     * 异常或普通
     *
     * @param message Toast 消息
     */
    @Override
    public void showToast(String message) {
        ToastUtil.toToast(message);
    }

    /**
     * View的点击事件都必须覆盖此方法
     */
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_onback) {
            finish();
        }
    }

    static {
        //可以使用Vector
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposables.clear();
    }

    @Override
    public void addNetworkRequest(Disposable d) {
        mDisposables.add(d);
    }


    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void tokenInvalid() {
//        UserManager.resetUserInfo();
//        ActivityUtils.finishAllActivities();
//        ARouter.getInstance().build(RouterContact.Account.LOGIN).navigation(this);
//        finish();
    }
}
