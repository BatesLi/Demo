package cn.dankal.demo.interfaceTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;

//程序员 A 和 B 之间需要协作，A 告诉 B 这个任务中间的某个环节需要 B 来完成，并且完成后告诉 A，
// 这时候程序员 A 就需要告诉 B 一个联系方式，使 B 完成时来通知自己，这个场景就可以使用回调，
public class InterfaceTestActivity extends AppCompatActivity {

  @BindView(R.id.txt_result_interface) TextView mTxtResultInterface;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_interface_test);
    ButterKnife.bind(this);
  }
}
