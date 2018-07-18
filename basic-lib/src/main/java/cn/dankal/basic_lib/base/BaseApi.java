package cn.dankal.basic_lib.base;



import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.util.concurrent.TimeUnit;

import cn.dankal.basic_lib.domain.TokenInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * @author leaflc
 */
public class BaseApi {

    private static final String BASE_URL = "https://api-template.dankal.cn/v1/";


    public static Retrofit getRetrofit() {
        return new Retrofit.Builder().addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkhttpInstance())
                .baseUrl(BASE_URL)
                .build();
    }

    private static OkHttpClient getOkhttpInstance() {

        OkHttpClient
                okHttpClient = new OkHttpClient.Builder().readTimeout(7000, TimeUnit.MILLISECONDS)
                .connectTimeout(7000, TimeUnit.MILLISECONDS)
                .addInterceptor(new TokenInterceptor())
                .hostnameVerifier(new HostnameVerifier() {

                    /**
                     * Verify that the host name is an acceptable match with
                     * the server's authentication scheme.
                     *
                     * @param hostname the host name
                     * @param session  SSLSession used on the connection to host
                     * @return true if the host name is acceptable
                     */
                    //忽略证书
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .build();
        return okHttpClient;
    }
}
