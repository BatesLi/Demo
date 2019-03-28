package cn.dankal.demo.skip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import cn.dankal.demo.R;

public class SkipActivity extends AppCompatActivity {

  public static final String STATE_SCORE = "playerScore";
  public static final String STATE_LEVEL = "playerLevel";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_skip);
    if (savedInstanceState != null) {
      savedInstanceState.getInt(STATE_LEVEL);
      savedInstanceState.getInt(STATE_SCORE);
    }
  }

  @Override
  public void onSaveInstanceState(Bundle savedInstanceState) {
    super.onSaveInstanceState(savedInstanceState);
    savedInstanceState.putInt(STATE_LEVEL, 2);
    savedInstanceState.putInt(STATE_SCORE, 3333);
  }

  @Override
  public void onPause() {
    super.onPause();
  }
}
