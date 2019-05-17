package cn.dankal.demo.Read.inject.module;

import cn.dankal.demo.Read.http.service.TopNewsService;
import cn.dankal.demo.Read.inject.qualifier.TopNewsUrl;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by quantan.liu on 2017/4/8.
 */
@Module
public class TopNewsHttpModule extends BaseHttpModule {
    @Singleton
    @Provides
    @TopNewsUrl
    Retrofit provideTopNewsRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, TopNewsService.API_TOPNEWS);
    }

    @Singleton
    @Provides
    TopNewsService provideTopNewsService(@TopNewsUrl Retrofit retrofit) {
        return retrofit.create(TopNewsService.class);
    }

}
