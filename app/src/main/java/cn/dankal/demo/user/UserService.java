package cn.dankal.demo.user;

import java.util.Map;

import cn.dankal.basic_lib.pojo.LoginResponse;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author leaflc
 */
public interface UserService {

    @GET("app/user/{mobile}")
    Observable<String> getVerifyCode(@Path("mobile") String mobile);

    @Headers("Content-Type: application/json")
    @POST("app/user")
    Observable<LoginResponse> signIn(@Body Map<String, String> params);
}
