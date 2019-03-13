package cn.dankal.demo.RetrofitRxjavaEventBus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.dankal.demo.R;
import cn.dankal.demo.RetrofitRxjavaEventBus.bean.MovieEntity;
import cn.dankal.demo.RetrofitRxjavaEventBus.utlis.HttpMethods;
import cn.dankal.demo.RetrofitRxjavaEventBus.utlis.ProgressSubscriber;
import cn.dankal.demo.RetrofitRxjavaEventBus.utlis.SubscriberOnNextListener;
import java.util.List;

public class RetrofitRxJavaActivity extends AppCompatActivity {

  @BindView(R.id.btn_click_me) Button mBtnClickMe;
  @BindView(R.id.txt_result_TV) TextView mTxtResultTv;

  public SubscriberOnNextListener subscriberOnNextListener;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_retrofit_rx_java);
    ButterKnife.bind(this);

    subscriberOnNextListener = new SubscriberOnNextListener<List<MovieEntity>>() {
      @Override public void onNext(List<MovieEntity> movieEntities) {
        mTxtResultTv.setText(movieEntities.toString());
      }
    };
  }
  @OnClick(R.id.btn_click_me) public void getClickMeJson() {
    getNetworkData();
  }

  public void getNetworkData() {
    /*String baseUrl = "https://api.douban.com/v2/movie/";
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();

    MovieService service = retrofit.create(MovieService.class);
    service.getTopMovie(0,10)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<MovieEntity>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {

          }

          @Override public void onNext(MovieEntity movieEntity) {

          }
        });*/
   /* HttpMethods.getMovieService()
        .getTopMovie(5,10 )
     .subscribeOn(Schedulers.io())
     .observeOn(AndroidSchedulers.mainThread())
     .subscribe(new Subscriber<MovieEntity>() {
       @Override public void onCompleted() {
         ToastUtil.toToast("完成");
       }

       @Override public void onError(Throwable e) {
          mTxtResultTv.setText(e.getMessage());
       }

       @Override public void onNext(MovieEntity movieEntity) {
        mTxtResultTv.setText(movieEntity.toString());
       }
     });*/
   /*HttpMethods.getMovieInstance().getTopMovie(new Subscriber<MovieEntity>() {
     @Override public void onCompleted() {
        ToastUtil.toToast("完成");
     }

     @Override public void onError(Throwable e) {
        mTxtResultTv.setText(e.getMessage());
     }

     @Override public void onNext(MovieEntity movieEntity) {
      mTxtResultTv.setText(movieEntity.getTitle());
     }
   }, 0, 10);*/
    HttpMethods.getMovieInstance()
        .getTopMovie(new ProgressSubscriber<>(subscriberOnNextListener, this)
            , 0, 10);
  }
}
