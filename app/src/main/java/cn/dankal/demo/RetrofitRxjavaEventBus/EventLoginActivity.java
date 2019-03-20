package cn.dankal.demo.RetrofitRxjavaEventBus;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.dankal.basic_lib.util.ToastUtil;
import cn.dankal.demo.R;
import cn.dankal.demo.RetrofitRxjavaEventBus.bean.LoginSeccessEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventLoginActivity extends Activity {

  @BindView(R.id.activity_main) RelativeLayout activityMain;
  @BindView(R.id.txt_show) TextView mTxtShow;
  @BindView(R.id.btn_show) Button mBtnShow;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_event_bus);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.btn_show) void onBtnShowClick() {
    if (!EventBus.getDefault().isRegistered(this)) {
      EventBus.getDefault().register(this);
    } else {
      ToastUtil.toToast("不要重复注册事件");
    }
  }

  @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
  public void onEventMainThread(LoginSeccessEvent event) {
    mTxtShow.setText("用户名:" + event.getUsername() + "| 密码" + event.getPassword());
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
  }
}
