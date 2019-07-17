package cn.dankal.demo.SearchPractise.SearchWanAndroid.Api;

public interface NetTask<T> {
  void execute(LoadTasksCallBack callBack, int... params);

  void execute(LoadTasksCallBack callBack, String... infos);
}
