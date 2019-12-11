package cn.dankal.demo.SearchPractise.MVP.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.basic_lib.util.ToastUtil;
import cn.dankal.demo.R;

public class UserActivity extends AppCompatActivity implements IUserView {

    public UserPresenter mUserPresenter;
    @BindView(R.id.edit_user_name)
    EditText mEditUserName;
    @BindView(R.id.edit_user_password)
    EditText mEditUserPassword;
    @BindView(R.id.view_code_divider)
    View mViewCodeDivider;
    @BindView(R.id.btn_user_login)
    Button mBtnUserLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        mUserPresenter = new UserPresenter(this);

        mBtnUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserPresenter.doLogin(mEditUserName.getText().toString(),
                        mEditUserPassword.getText().toString());
            }
        });
    }

    @Override
    public void doLoginResult(boolean rec, int code) {
        switch (code) {
            case 0:
                ToastUtil.toToast("登录成功");
                break;
            case -1:
                ToastUtil.toToast("密码或者登录名错误，请重新输入");
                break;
            default:
                break;
        }
    }

    @Override
    public void clear() {

    }
}
