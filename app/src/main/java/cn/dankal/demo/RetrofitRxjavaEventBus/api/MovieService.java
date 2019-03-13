package cn.dankal.demo.RetrofitRxjavaEventBus.api;

import cn.dankal.demo.RetrofitRxjavaEventBus.bean.HttpResult;
import cn.dankal.demo.RetrofitRxjavaEventBus.bean.MovieEntity;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface MovieService {
  //https://api.douban.com/v2/movie/top250?start=0&count=10
  //@Query和@QueryMap
  //作用：用于 @GET 方法的查询参数（Query = Url 中 ‘?’ 后面的 key-value）
  @GET("top250")
  Observable<HttpResult<List<MovieEntity>>> getMovie(@Query("start") int start,
      @Query("count") int count);
}
