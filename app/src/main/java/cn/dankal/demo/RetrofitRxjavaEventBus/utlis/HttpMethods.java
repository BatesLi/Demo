package cn.dankal.demo.RetrofitRxjavaEventBus.utlis;

import cn.dankal.demo.RetrofitRxjavaEventBus.api.MovieService;
import cn.dankal.demo.RetrofitRxjavaEventBus.bean.HttpResult;
import cn.dankal.demo.RetrofitRxjavaEventBus.bean.MovieEntity;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class HttpMethods {

  public static final String BASE_URL = "https://api.douban.com/v2/movie/";
  private Retrofit mRetrofit;
  private MovieService mMovieService;

  //在类创建的实话构造函数就会开始初始化 | 静态内部类单例模式
  private HttpMethods() {
    //延时时间
    OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
    httpClientBuilder.connectTimeout(5, TimeUnit.SECONDS);

    mRetrofit = new Retrofit.Builder()
        .client(httpClientBuilder.build())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build();

    mMovieService = mRetrofit.create(MovieService.class);
  }

  //创建单例
  private static class SingleTonHolder {
    private static final HttpMethods httpMethods = new HttpMethods();
  }

  public static HttpMethods getMovieInstance() {
    return SingleTonHolder.httpMethods;
  }

  //subscriber 由调用者传过来的观察者对象
  /*public void getTopMovie(Subscriber<MovieEntity> subscriber,int start,int count) {
    mMovieService.getTopMovie(start,count)
        .subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(subscriber);
  }*/
  /*public static MovieService getMovieService() {
   Retrofit mRetrofit = new Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build();

    return mRetrofit.create(MovieService.class);
  }*/
  /*
   * 用来统一处理http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber;
   * Subscriber真正需要的数据类型,Data数据部分
   * */
  private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

    @Override public T call(HttpResult<T> httpResult) {
      if (httpResult.getResultCode() != 0) {
        throw new ApiException(httpResult.getResultCode());
      }
      return httpResult.getData();
    }
  }

  public void getTopMovie(Subscriber<List<MovieEntity>> subscriber, int start, int count) {
    /*
     * 由于HttpResult中的泛型T就是我们希望传递给subscriber的数据类型，而数据可以通过httpResult的getData方法获得
     * ，这样我们就处理了泛型问题，错误处理问题，还有将请求数据部分剥离出来给subscriber
     * */
   /* mMovieService.getMovie(start,count)
        .map(new HttpResultFunc<List<MovieEntity>>())
        .subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(subscriber);*/
    Observable observable = mMovieService.getMovie(start, count)
        .map(new HttpResultFunc<List<MovieEntity>>());

    toSubscribe(observable, subscriber);
  }

  private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
    o.subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(s);
  }
}






