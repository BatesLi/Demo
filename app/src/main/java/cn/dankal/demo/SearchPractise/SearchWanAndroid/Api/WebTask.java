package cn.dankal.demo.SearchPractise.SearchWanAndroid.Api;

import cn.dankal.demo.SearchPractise.SearchWanAndroid.Utils.StringUtils;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.model.Data;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.model.SearchResult;
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

  @Override public void execute(final LoadTasksCallBack callBack, final int... params) {
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

  @Override public void execute(final LoadTasksCallBack callBack, final String... infos) {
    switch (infos[0]) {
      case StringUtils.TYPE_HOTKEY_CONTENT_ADD:
      case StringUtils.TYPE_HOTKEY_CONTENT_LOAD:
        OkGo.<String>post(StringUtils.URL + StringUtils.HOT_KEY_CONTENT + infos[2] + "/json")
            .params("k", infos[1])
            .execute(new StringCallback() {
              @Override public void onSuccess(Response<String> response) {
                String result = response.body();
                Gson gson = new Gson();
                SearchResult searchResult = gson.fromJson(result, SearchResult.class);
                switch (searchResult.getErrorCode()) {
                  case 0:
                    if (infos[0].equals(StringUtils.TYPE_HOTKEY_CONTENT_ADD)) {
                      callBack.onSuccess(searchResult.getData(),
                          StringUtils.TYPE_HOME_MORE_ARTICLE_ADD);
                    } else {
                      callBack.onSuccess(searchResult.getData(),
                          StringUtils.TYPE_HOME_MORE_ARTICLE_LOAD);
                    }
                    break;
                  case -1:
                    callBack.onError(searchResult.getErrorCode(), searchResult.getErrorMsg());
                    break;
                  default:
                    callBack.onFailed();
                    break;
                }
              }
            });
        break;
      default:
        break;
    }
  }
}





