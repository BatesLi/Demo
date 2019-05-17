package cn.dankal.demo.Read.inject.component.fragment;

import cn.dankal.demo.Read.fragment.TopNewsFragment;
import cn.dankal.demo.Read.inject.module.TopNewsHttpModule;
import cn.dankal.demo.Read.inject.module.TopNewsModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {TopNewsHttpModule.class, TopNewsModule.class})
public interface TopNewsComponent{
  void injectTopNews(TopNewsFragment topNewsFragment);
}



