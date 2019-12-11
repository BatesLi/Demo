package cn.dankal.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.basic_lib.util.ToastUtil;

public class BubbleSortActivity extends AppCompatActivity {

  @BindView(R.id.edit_input_array)
  EditText mEditInputArray;
  @BindView(R.id.btn_click)
  Button mBtnClick;
  @BindView(R.id.txt_result)
  TextView mTxtResult;
  @BindView(R.id.txt_max)
  TextView mTxtMax;
  @BindView(R.id.txt_mini)
  TextView mTxtMini;

  private int[] bubble = {2, 56, 28, 18, 9, 44, 32};
  private int temp;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bubble_sort);
    ButterKnife.bind(this);

    for (int i = 0; i < bubble.length; i++) { //外层for循环控制 比较的轮数
      for (int j = 0; j < bubble.length - i - 1; j++) {//内层控制 对比的次数
        if (bubble[j] > bubble[j + 1]) {
          temp = bubble[j];
          bubble[j] = bubble[j + 1];
          bubble[j + 1] = temp;
        }
      }
    }
    mBtnClick.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mTxtResult.setText(Arrays.toString(setArrar(3, 4, 5, 6, 777, 77)));
      }
    });
  }

  public int[] setArrar(int... strings) {
    for (int i = 0; i < strings.length; i++) {
      for (int j = 0; j < strings.length - i - 1; j++) {
        if (strings[j] > strings[j + 1]) {
          strings[j] = strings[j + 1];
          int temp = strings[j];
          strings[j + 1] = temp;
        }
      }
    }
    return strings;
  }
}















