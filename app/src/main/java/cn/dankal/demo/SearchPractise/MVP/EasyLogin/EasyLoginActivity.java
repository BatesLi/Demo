package cn.dankal.demo.SearchPractise.MVP.EasyLogin;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.basic_lib.util.ToastUtil;
import cn.dankal.demo.R;

public class EasyLoginActivity extends AppCompatActivity {

    @BindView(R.id.iv_logo)
    ImageView mIvLogo;
    @BindView(R.id.et_account)
    TextInputEditText mEtAccount;
    @BindView(R.id.et_password)
    TextInputEditText mEtPassword;
    @BindView(R.id.input_layout_password)
    TextInputLayout mInputLayoutPassword;
    @BindView(R.id.linear_form)
    LinearLayout mLinearForm;
    @BindView(R.id.app_compat_check_remember_password)
    AppCompatCheckBox mAppCompatCheckRememberPassword;
    @BindView(R.id.btn_forget_password)
    AppCompatButton mBtnForgetPassword;
    @BindView(R.id.btn_login)
    AppCompatButton mBtnLogin;
    @BindView(R.id.btn_to_register)
    AppCompatButton mBtnToRegister;
    @BindView(R.id.txt_third_part_login_label)
    TextView mTxtThirdPartLoginLabel;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.img_btn_phone_code_login)
    ImageButton mImgBtnPhoneCodeLogin;
    @BindView(R.id.img_btn_wechat_login)
    ImageButton mImgBtnWechatLogin;
    @BindView(R.id.img_btn_trial_login)
    ImageButton mImgBtnTrialLogin;
    @BindView(R.id.content_view)
    ConstraintLayout mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_login);
        ButterKnife.bind(this);

    }
}
