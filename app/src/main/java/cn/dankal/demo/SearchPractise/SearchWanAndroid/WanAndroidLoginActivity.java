package cn.dankal.demo.SearchPractise.SearchWanAndroid;

import static com.kongzue.dialog.v2.DialogSettings.STYLE_KONGZUE;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.dankal.basic_lib.util.ToastUtil;
import cn.dankal.demo.R;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.Api.WanAndoridLoginContact;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.Api.WebTask;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.base.BaseActivity;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.custom.ClearEditText;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.model.Data;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.presenter.WanAndroidLoginPresenter;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.kongzue.dialog.v2.Notification;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class WanAndroidLoginActivity extends BaseActivity
    implements WanAndoridLoginContact.LoginView
    , OnTitleBarListener {

  public WanAndoridLoginContact.LoginPresenter mWanAndroidLoginPresenter;
  @BindView(R.id.title_bar_login) TitleBar mTitleBarLogin;
  @BindView(R.id.txt_login_title) TextView mTxtLoginTitle;
  @BindView(R.id.layout_title) LinearLayout mLayoutTitle;
  @BindView(R.id.img_user_picture) ImageView mImgUserPicture;
  @BindView(R.id.edit_user_name_input) ClearEditText mEditUserNameInput;
  @BindView(R.id.img_password_picture) ImageView mImgPasswordPicture;
  @BindView(R.id.edit_password_input) ClearEditText mEditPasswordInput;
  @BindView(R.id.user_line_confirm) View mUserLineConfirm;
  @BindView(R.id.linear_layout_password_confirm) LinearLayout mLinearLayoutPasswordConfirm;
  @BindView(R.id.img_password_confirm_picture) ImageView mImgPasswordConfirmPicture;
  @BindView(R.id.edit_password_confirm_input) ClearEditText mEditPasswordConfirmInput;
  @BindView(R.id.card_view_login) CardView mCardViewLogin;
  @BindView(R.id.btn_login) Button mBtnLogin;

  @BindString(R.string.user_btn_register) String strRegister;
  @BindString(R.string.login) String strLogin;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_wan_android_login);
    ButterKnife.bind(this);
    doAnimation(0);
    EventBus.getDefault().register(this);

    mTitleBarLogin.setRightTitle("注册");
    mTitleBarLogin.setOnTitleBarListener(this);

    if (mWanAndroidLoginPresenter == null) {
      mWanAndroidLoginPresenter = WanAndroidLoginPresenter
          .createWanAndroidLoginPresenter(this, WebTask.getInstance());
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
  }

  //通过EventBus，更新UI?
  @Subscribe

  /**
   * 根据login的按钮显示的文字来进行条件判断
   * @param view
   */
  @OnClick(R.id.btn_login)
  public void LoginOnClick(View view) {
    if (!TextUtils.isEmpty(mEditUserNameInput.getText().toString())
        && !TextUtils.isEmpty(mEditPasswordInput.getText().toString())) {
      if (strLogin.equals(mBtnLogin.getText().toString())) {
        mWanAndroidLoginPresenter.getLogin(mEditUserNameInput.getText().toString(),
            mEditPasswordInput.getText().toString());
      }
      if (strRegister.equals(mBtnLogin.getText().toString())) {
        if (!TextUtils.isEmpty(mEditPasswordConfirmInput.getText().toString())) {
          if (mEditPasswordInput.getText()
              .toString()
              .equals(mEditPasswordConfirmInput.getText().toString())) {
            mWanAndroidLoginPresenter.getRegister(mEditUserNameInput.getText().toString(),
                mEditPasswordInput.getText().toString());
          } else {
            ToastUtil.toToast("两次输入的密码不一致！");
            return;
          }
        } else {
          ToastUtil.toToast("请二次输入密码");
        }
      }
    } else if (TextUtils.isEmpty(mEditUserNameInput.getText().toString())
        && TextUtils.isEmpty(mEditPasswordInput.getText().toString())) {
      ToastUtil.toToast("尚未输入用户名与密码");
    } else if (TextUtils.isEmpty(mEditUserNameInput.getText().toString())) {
      ToastUtil.toToast("尚未输入用户名");
    } else if (TextUtils.isEmpty(mEditPasswordInput.getText().toString())) {
      ToastUtil.toToast("尚未输入密码");
    }
  }

  @Override public void onLeftClick(View v) {

  }

  @Override public void onTitleClick(View v) {

  }

  @Override public void onRightClick(View v) {
    if (strLogin.equals(mBtnLogin.getText().toString())) {
      mTitleBarLogin.setRightTitle("登录");
      mBtnLogin.setText("注册");
      doAnimation(2);

      Notification.show(this, 0, "注册账号无需使用手机、邮箱等个人信息。"
          , Notification.TYPE_ERROR).setDialogStyle(STYLE_KONGZUE);
    } else {
      mTitleBarLogin.setRightTitle("注册");
      mBtnLogin.setText("登录");
      doAnimation(1);
    }
  }

  @Override public void LoginSuccess(Data data) {
    ToastUtil.toToast("登录成功");
    finish();
  }

  @Override public void setRegister(String... info) {
    //需要回调到 P吗？参数是传递一个数组? 已经把错误的参数回调回来了,是一个数组，然后如何显示？
    //应该用一个dialog把 错误的参数显示出来
   /* for (int i = 0;i < info.length;i++) {
      ToastUtil.toToast(info[0]);
    }*/
    if (!TextUtils.isEmpty(info[0])) {
      ToastUtil.toToast(info[0]);
    }
  }

  @Override public void setLoginLocation(int height) {

  }

  @Override public void refreshLocation(int height) {

  }

  @Override public void setPresenter(WanAndoridLoginContact.LoginPresenter presenter) {
    this.mWanAndroidLoginPresenter = presenter;
  }

  public void doAnimation(int flag) {
    //属性动画？解决add布局，导致父布局里面的子控件显示不全的问题
    ObjectAnimator alphaTitleHide = ObjectAnimator.ofFloat(mTxtLoginTitle, "alpha", 1, 0);
    ObjectAnimator alphaTitleShow = ObjectAnimator.ofFloat(mTxtLoginTitle, "alpha", 0, 1);
    //View.getTranslationX()计算的是该View在X轴的偏移量。初始值为0，向左偏移值为负，向右偏移值为正。 
    //View.getTranslationY()计算的是该View在Y轴的偏移量。初始值为0，向上偏移为负，向下偏移为正。
    Float OldCardHeight = mCardViewLogin.getTranslationY();
    Float OldLoginHeight = mBtnLogin.getTranslationY();
    Float OldRegisterHeight = mTitleBarLogin.getTranslationY();

    ObjectAnimator alphaCardView = ObjectAnimator.ofFloat(mCardViewLogin, "alpha", 0, 1);
    ObjectAnimator alphaLoginBtn = ObjectAnimator.ofFloat(mBtnLogin, "alpha", 0, 1);
    ObjectAnimator alphaRegisterNow = ObjectAnimator.ofFloat(mTitleBarLogin, "alpha", 0, 1);

    ObjectAnimator translationYCardView = ObjectAnimator.ofFloat(mCardViewLogin,
        "translationY", mCardViewLogin.getTranslationY(), -45, OldCardHeight + 20, OldCardHeight);
    ObjectAnimator translationYLoginBtn = ObjectAnimator.ofFloat(mBtnLogin,
        "translationY", mBtnLogin.getTranslationY(), -45, OldLoginHeight + 20, OldLoginHeight);
    ObjectAnimator translationYRegisterNow = ObjectAnimator.ofFloat(mTitleBarLogin,
        "translationY", mTitleBarLogin.getTranslationY(), -45, OldRegisterHeight + 20,
        OldRegisterHeight);

    ObjectAnimator alphaLinHide = ObjectAnimator.ofFloat(mUserLineConfirm, "alpha", 1, 0);
    ObjectAnimator alphaLinShow = ObjectAnimator.ofFloat(mUserLineConfirm, "alpha", 0, 1);
    ObjectAnimator alphaConfirmHide =
        ObjectAnimator.ofFloat(mLinearLayoutPasswordConfirm, "alpha", 1, 0);
    ObjectAnimator alphaConfirmShow =
        ObjectAnimator.ofFloat(mLinearLayoutPasswordConfirm, "alpha", 0, 1);

    switch (flag) {
      case 0:
        //show
        //先实现cardView的Y轴移动动画效果
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationYCardView, translationYLoginBtn,
            translationYRegisterNow);
        animatorSet.setDuration(1000).start();
        break;
      //消失
      case 1:
        alphaLinHide.setDuration(250).start();
        alphaTitleHide.setDuration(250).start();
        alphaTitleHide.addListener(new Animator.AnimatorListener() {
          @Override public void onAnimationStart(Animator animation) {

          }

          @Override public void onAnimationEnd(Animator animation) {
            mTxtLoginTitle.setText("登录");
            alphaTitleShow.setDuration(250).start();
            alphaConfirmHide.setDuration(2000).start();
          }

          @Override public void onAnimationCancel(Animator animation) {

          }

          @Override public void onAnimationRepeat(Animator animation) {

          }
        });
        AnimatorSet alphaHide = new AnimatorSet();
        alphaHide.playTogether(alphaLinHide, alphaConfirmHide);
        alphaHide.setDuration(500).start();
        alphaHide.addListener(new Animator.AnimatorListener() {
          @Override public void onAnimationStart(Animator animation) {

          }

          @Override public void onAnimationEnd(Animator animation) {
            mUserLineConfirm.setVisibility(View.GONE);
            mLinearLayoutPasswordConfirm.setVisibility(View.GONE);
          }

          @Override public void onAnimationCancel(Animator animation) {

          }

          @Override public void onAnimationRepeat(Animator animation) {

          }
        });
        break;
      //显示
      case 2:
        alphaTitleHide.setDuration(250).start();
        alphaLinShow.setDuration(250).start();
        alphaTitleHide.addListener(new Animator.AnimatorListener() {
          @Override public void onAnimationStart(Animator animation) {

          }

          @Override public void onAnimationEnd(Animator animation) {
            mTxtLoginTitle.setText("注册");
            alphaTitleShow.setDuration(250).start();
            alphaConfirmShow.setDuration(2000).start();
          }

          @Override public void onAnimationCancel(Animator animation) {

          }

          @Override public void onAnimationRepeat(Animator animation) {

          }
        });
        mUserLineConfirm.setVisibility(View.VISIBLE);
        mLinearLayoutPasswordConfirm.setVisibility(View.VISIBLE);
        break;
      default:
        break;
    }
  }
}
