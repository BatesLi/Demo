package cn.dankal.demo.RetrofitRxjavaEventBus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.dankal.basic_lib.util.ToastUtil;
import cn.dankal.demo.R;
import cn.dankal.demo.RetrofitRxjavaEventBus.bean.LoginSeccessEvent;
import org.greenrobot.eventbus.EventBus;

public class EventBusActivity extends AppCompatActivity {

  @BindView(R.id.edit_username) EditText mEditUserName;
  @BindView(R.id.edit_password) EditText mEditPassword;
  @BindView(R.id.btn_login) Button mBtnLogin;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_event_login);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.btn_login)
  public void setBtnLogin() {
    if ("Elaine".equals(getUserName()) && "123".equals(getUserPassword())) {
      Intent intent = new Intent(this, EventLoginActivity.class);
      startActivity(intent);
      ToastUtil.toToast("登录成功");

      LoginSeccessEvent seccessEvent = new LoginSeccessEvent();
      seccessEvent.setUsername(getUserName());
      seccessEvent.setPassword(getUserPassword());
      EventBus.getDefault().postSticky(seccessEvent);
    } else {
      ToastUtil.toToast("账号或密码错误");
    }
  }

  private String getUserName() {
    return mEditUserName.getText().toString().trim();
  }

  private String getUserPassword() {
    return mEditPassword.getText().toString().trim();
  }
}
