package cn.dankal.demo.skip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;
import java.util.Arrays;

public class ArithmeticActivity extends AppCompatActivity {

  @BindView(R.id.txt_arithmetic_result) TextView mTxtArithmeticResult;

  public int[] numbers = {4, 5, 6, 9, 33, 54, 32, 123, 78};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_arithmetic);
    ButterKnife.bind(this);

    BubbleSort(numbers);
    mTxtArithmeticResult.setText("排序之后的结果:" + Arrays.toString(numbers));
  }

  //双重for循环,外层循环执行一次 里层循环执行多次
  private void BubbleSort(int[] numbers) {
    for (int i = 0; i < numbers.length - 1; i++) {
      for (int j = 0; j < numbers.length - 1 - i; j++) {
        if (numbers[j] > numbers[j + 1]) {
          //错的
             /* numbers[j] = numbers[j+1];
              temp = numbers[j];
              numbers[j] = numbers[j+1];*/
          int tmp = numbers[j];
          numbers[j] = numbers[j + 1];
          numbers[j + 1] = tmp;
        }
      }
    }
  }
}
