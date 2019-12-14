package cn.dankal.demo.user;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import cn.dankal.basic_lib.base.BaseActivity;
import cn.dankal.demo.R;
import cn.dankal.demo.indicator.IndicatorViewPageActivity;

/**
 * @author leaflc
 */
public class LoginActivity extends BaseActivity implements LoginContact.LoginView {

    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_verify_code)
    EditText etVerifyCode;
    @BindView(R.id.view_code_divider)
    View viewCodeDivider;
    @BindView(R.id.bt_verify_code)
    Button btVerifyCode;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.btn_indicator)
    Button mBtnIndicator;

    private LoginPresenter presenter = new LoginPresenter();
    private TimeCount timeCount;
    private String tempCode;

    @OnTextChanged({R.id.et_mobile, R.id.et_verify_code})
    void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (!etMobile.getText().toString().isEmpty() && !etVerifyCode.getText().toString().isEmpty()) {
            btLogin.setBackgroundColor(ContextCompat.getColor(this, R.color.colorSub));
            btLogin.setEnabled(true);
        } else {
            btLogin.setBackgroundColor(ContextCompat.getColor(this, R.color.colorUnClick));
            btLogin.setEnabled(false);
        }
    }

    @OnTextChanged(R.id.et_mobile)
    void onTextMobileChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (etMobile.getText().toString().length() == 11) {
            btVerifyCode.setBackgroundColor(ContextCompat.getColor(this, R.color.colorSub));
            btVerifyCode.setEnabled(true);
        } else {
            btVerifyCode.setBackgroundColor(ContextCompat.getColor(this, R.color.colorUnClick));
            btVerifyCode.setEnabled(false);
        }
    }

    @Override
    protected int contentViewLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initComponents() {
        presenter.attachView(this);
        timeCount = new TimeCount(60000, 1000);
        setStatusBarAndToolBar(true, true, 0);
    }

    @Override
    public void loginSuccess() {
        showToast("登录成功");
    }

    @Override
    public void getVerifyCodeSuccess(String tempCode) {
        timeCount.start();
        this.tempCode = tempCode;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        timeCount.cancel();
    }

    @OnClick({R.id.bt_verify_code, R.id.bt_login, R.id.btn_indicator})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_verify_code:
                presenter.getVerifyCode(etMobile.getText().toString());
                break;
            case R.id.bt_login:
                if (tempCode==null){
                    showToast("请先获取验证码");
                    return;
                }
                presenter.login(etMobile.getText().toString(), etVerifyCode.getText().toString(), tempCode);
                break;
            case R.id.btn_indicator:
                Intent indicatorIntent = new Intent(this, IndicatorViewPageActivity.class);
                startActivity(indicatorIntent);
                break;
            default:
                break;
        }
    }


    /**
     * 验证码倒计时
     */
    class TimeCount extends CountDownTimer {

        private TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btVerifyCode.setEnabled(false);
            btVerifyCode.setText(getResources().getString(R.string.tv_get_code) + "(" + millisUntilFinished / 1000 + ")");
        }

        @Override
        public void onFinish() {
            btVerifyCode.setText(getResources().getString(R.string.get_verify_code));
            btVerifyCode.setEnabled(true);
        }
    }
}
