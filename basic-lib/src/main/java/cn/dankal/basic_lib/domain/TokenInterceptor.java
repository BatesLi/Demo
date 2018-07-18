package cn.dankal.basic_lib.domain;

import java.io.IOException;

import cn.dankal.manager.UserManager;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        if (UserManager.unLogined()) {
            return chain.proceed(originalRequest);
        } else {
            String token = UserManager.getUserInfo().getAccessToken();
            Request requestAuthorised = originalRequest.newBuilder()
                    .header("X-Access-Token", token)
                    .build();
            return chain.proceed(requestAuthorised);
        }
    }
}
