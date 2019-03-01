package cn.dankal.demo.MemoryLeak;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import cn.dankal.demo.R;
import cn.dankal.demo.user.LoginActivity;

public class MemoryLeakTwoActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_memory_leak_two);

    LoginActivity.activity = this;
  }
}
