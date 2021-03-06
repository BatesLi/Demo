package cn.dankal.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HandlerActivity extends AppCompatActivity {

  @BindView(R.id.btn_one) Button mBtnOne;
  @BindView(R.id.txt_show) TextView mTxtShow;
//理解，记住，运用
  private Handler handler = new Handler() {
  int counter = 0;
  @Override
  public void handleMessage(Message message) {
    switch (message.what) {
      case 0:
        counter++;
        mTxtShow.setText("下载中" + counter + "%");
        break;
      case 1:
        mTxtShow.setText("下载完成");
        break;
        default:
          break;
    }
  }
};
  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_handler);
      ButterKnife.bind(this);


      mBtnOne.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          //这种直接新建的子线程Thread和AsyncTask都是匿名内部类对象，
          // 默认就隐式的持有外部Activity的引用，导致Activity内存泄露
          new Thread() {
            @Override
            public void run() {
              for (int i = 0;i < 100;i++) {
                  try {
                    Thread.sleep(500);
                  }catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                  handler.sendEmptyMessage(0);
              }
              handler.sendEmptyMessage(1);
            }
          }.start();
        }
      });
  }
}
