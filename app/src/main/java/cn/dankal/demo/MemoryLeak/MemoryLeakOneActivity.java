package cn.dankal.demo.MemoryLeak;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import cn.dankal.demo.R;

public class MemoryLeakOneActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_memory_leak_one);

    new Handler().postDelayed(new Runnable() {
      @Override public void run() {

      }
    }, 1000000);
  }
}
