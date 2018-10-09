package cn.dankal.demo.mvp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;

//启动APP的开场动画 activity
public class StartActivity extends AppCompatActivity {

  @BindView(R.id.img_start) ImageView mImgStart;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_start);
    ButterKnife.bind(this);
    initImage();
  }

  private void initImage() {
    mImgStart.setImageResource(R.drawable.start_animation);
    //进行缩放动画
    ScaleAnimation scaleAnimation = new ScaleAnimation(1.4f, 1.0f, 1.4f, 1.0f,
        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
    scaleAnimation.setDuration(4000);
    //动画播放完成后保持形状
    scaleAnimation.setFillAfter(true);
    scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
      @Override
      public void onAnimationStart(Animation animation) {

      }

      @Override
      public void onAnimationEnd(Animation animation) {
        startActivity();
      }

      @Override
      public void onAnimationRepeat(Animation animation) {

      }
    });
    mImgStart.startAnimation(scaleAnimation);
  }

  private void startActivity() {
   /* Intent intent = new Intent(StartActivity.this,BaseMvpActivity.class);
    startActivity(intent);*/
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    finish();
  }
}
