package cn.dankal.demo.Read.inject.module;

import cn.dankal.demo.Read.adapter.TopNewsAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;
import javax.inject.Singleton;

/**
 * Created by quantan.liu on 2017/4/8.
 */
@Module
public class TopNewsModule {
    @Provides
    @Singleton
    public BaseQuickAdapter provideAdapter() {
        return new TopNewsAdapter(new ArrayList());
    }
}
