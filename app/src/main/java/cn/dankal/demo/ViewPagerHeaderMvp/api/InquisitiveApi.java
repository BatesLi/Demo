package cn.dankal.demo.ViewPagerHeaderMvp.api;

import cn.dankal.demo.ViewPagerHeaderMvp.bean.Inquisitive;
import cn.dankal.demo.ViewPagerHeaderMvp.bean.InquisitiveBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface InquisitiveApi {
  //http://app3.qdaily.com/app3/homes/index/0.json
  @GET("homes/index/{num}.json")
  Call<InquisitiveBean> getInquisitiveData(@Path("num") String num);
}
