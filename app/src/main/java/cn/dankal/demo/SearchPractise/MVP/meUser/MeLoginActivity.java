package cn.dankal.demo.SearchPractise.MVP.meUser;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.basic_lib.util.ToastUtil;
import cn.dankal.demo.R;

public class MeLoginActivity extends AppCompatActivity implements IMeUserView {

    @BindView(R.id.edit_user_name)
    EditText mEditUserName;
    @BindView(R.id.edit_user_password)
    EditText mEditUserPassword;
    @BindView(R.id.view_code_divider)
    View mViewCodeDivider;
    @BindView(R.id.btn_user_login)
    Button mBtnUserLogin;

    private MeUserPresenter mMeUserPresenter;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        mProgressDialog = new ProgressDialog(this);
        mMeUserPresenter = new MeUserPresenter();
        mMeUserPresenter.attachView(this);

        mBtnUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMeUserPresenter.doLogin(mEditUserName.getText().toString()
                        , mEditUserPassword.getText().toString());
            }
        });
    }


    @Override
    public void showResult(String meg) {
        ToastUtil.toToast(meg);
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.toToast(msg);
    }

    @Override
    public void showLoading(String msg) {
        mProgressDialog.setMessage(msg);
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }
}
