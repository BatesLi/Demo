package cn.dankal.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BubbleSortActivity extends AppCompatActivity {

  @BindView(R.id.txt_result) TextView mTxtResult;
  @BindView(R.id.txt_max) TextView mTxtMax;
  @BindView(R.id.txt_mini) TextView mTxtMini;

  private int[] bubble = {2, 56, 28, 18, 9, 44, 32};
  private int temp;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bubble_sort);
    ButterKnife.bind(this);

    for (int i = 0; i < bubble.length - 1; i++) { //外层for循环控制 比较的轮数
      for (int j = 0; j < bubble.length - 1 - i; i++) {//内层控制 对比的次数
        if (bubble[j] < bubble[j + 1]) {
          temp = bubble[j];
          bubble[j] = bubble[j + 1];
          bubble[j + 1] = temp;
              /*bubble[i] = bubble[i+1];
              bubble[i+1] = bubble[i];*/
        }
      }
    }
  }
}















