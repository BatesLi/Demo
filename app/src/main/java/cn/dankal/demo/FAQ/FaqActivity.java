package cn.dankal.demo.FAQ;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;

public class FaqActivity extends AppCompatActivity {

  //@BindView(R.id.recycler_faq) RecyclerView mRecyclerFaq;
  //测试轮播功能，复习 handler用法与 轮播的逻辑
  @BindView(R.id.img_carousel) ImageView mImgCarousel;

  private int[] images = {R.mipmap.item1, R.mipmap.item2, R.mipmap.item3};
  private int index;
  private Handler handler = new Handler();
  private MyRunnable myRunnable = new MyRunnable();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_faq);
    ButterKnife.bind(this);

    handler.post(myRunnable);
  }

  class MyRunnable implements Runnable {
    @Override public void run() {
      index++;
      index = index % 3;
      mImgCarousel.setImageResource(images[index]);
      handler.postDelayed(myRunnable, 1000);
    }
  }
}
