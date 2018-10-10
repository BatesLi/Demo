package cn.dankal.demo.ViewPagerHeaderMvp.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class InquisitiveServe {

  private static InquisitiveApi getInquisitiveData = null;

  public static InquisitiveApi getInquisitiveData() {
    if (getInquisitiveData == null) {
      synchronized (InquisitiveApi.class) {
        if (getInquisitiveData == null) {
          Retrofit retrofit = new Retrofit.Builder()
              .baseUrl("http://app3.qdaily.com/app3/")
              .addConverterFactory(GsonConverterFactory.create())
              .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
              .build();
          getInquisitiveData = retrofit.create(InquisitiveApi.class);
        }
      }
    }
    return getInquisitiveData;
  }
}









