package cn.dankal.demo.SearchPractise.SearchWanAndroid.Api;

import cn.dankal.demo.SearchPractise.SearchWanAndroid.Utils.StringUtils;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.model.Data;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.model.WanAndroid;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class WebTask implements NetTask<Data> {

  private static final String TAG = "WebTask";
  private static WebTask INSTANCE = null;

  public WebTask() {
  }

  //单例模式
  public static WebTask getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new WebTask();
    }
    return INSTANCE;
  }

  @Override public void execute(LoadTasksCallBack callBack, int... params) {
    switch (params[0]) {
      case StringUtils.TYPE_HOT_KEY:
        OkGo.<String>get(StringUtils.URL + StringUtils.HOT_KEY)
            .execute(new StringCallback() {
              @Override public void onSuccess(Response<String> response) {
                String result = response.body();
                Gson gson = new Gson();
                //new Gson().fromJson(Json_string,class)，它会“尽量”转换出对象
                // ，哪怕得到的对象从数据上看是不完整的。
                WanAndroid wanAndroid = gson.fromJson(result, WanAndroid.class);
                if (wanAndroid.getErrorCode() == 0) {
                  callBack.onSuccess(wanAndroid.getData(), StringUtils.TYPE_HOT_KEY);
                }
              }
            });
        break;
      default:
        break;
    }
  }

  @Override public void execute(LoadTasksCallBack callBack, String... infos) {
    switch (infos[0]) {

    }
  }
}
