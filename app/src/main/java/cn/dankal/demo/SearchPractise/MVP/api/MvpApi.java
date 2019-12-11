package cn.dankal.demo.SearchPractise.MVP.api;

import cn.dankal.demo.SearchPractise.MVP.base.MvpData;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MvpApi {
    //https://wanandroid.com/wxarticle/chapters/json
    @GET("wxarticle/chapters/json")
    Call<MvpData> getMvpData();
}
