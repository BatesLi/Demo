package cn.dankal.demo.RetrofitRxjavaEventBus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.dankal.basic_lib.util.ToastUtil;
import cn.dankal.demo.R;

public class RetrofitRxJavaActivity extends AppCompatActivity {

  @BindView(R.id.btn_click_me) Button mBtnClickMe;
  @BindView(R.id.txt_result_TV) TextView mTxtResultTv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_retrofit_rx_java);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.btn_click_me) public void getClickMeJson() {
    ToastUtil.toToast("获取网络数据");
  }
}
