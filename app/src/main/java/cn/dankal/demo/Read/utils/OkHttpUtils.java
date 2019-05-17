package cn.dankal.demo.Read.utils;

import android.os.Handler;
import android.os.Looper;
import com.google.gson.internal.$Gson$Types;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
* @author BatesLi
* @emil libates52@gmail.com
* create at 2019/5/4
* description：OkHttp网络连接封装工具类
*/
public class OkHttpUtils {

  private static final String TAG = "OkHttpUtils";

  private static OkHttpUtils mInstance;
  private OkHttpClient mOkHttpClient;
  private Handler mDelivery;

  private OkHttpUtils() {
    mOkHttpClient = new OkHttpClient();
    mDelivery = new Handler(Looper.getMainLooper());
  }

  private synchronized static OkHttpUtils getmInstance() {
    if (mInstance == null) {
        mInstance = new OkHttpUtils();
    }
    return mInstance;
  }
  //okhttp3的使用：
  private void getRequest(String url,ResultCallback resultCallback) {
    final Request request = new Request.Builder().url(url).build();
    deliveryResult(resultCallback,request);
  }
  private void deliveryResult(final ResultCallback callback,Request request) {
    mOkHttpClient.newCall(request).enqueue(new Callback() {
      @Override public void onFailure(Call call, IOException e) {
        sendFailCallback(callback,e);
      }

      @Override public void onResponse(Call call, Response response) throws IOException {
        //抛出一个错误
        try {
          String str = response.body().string();
          if (callback.mType == String.class) {
              sendSuccessCallBack(callback,str);
          }else {
              Object object = JsonUtils.deserialize(str,callback.mType);
              sendSuccessCallBack(callback,object);
          }
        }catch (Exception e) {
          sendSuccessCallBack(callback,e);
        }
      }
    });
  }
  public void sendFailCallback(final ResultCallback callback,final Exception e) {
    //handler.post(r)。r是要执行的任务代码。
    // 意思就是说r的代码实际是在UI线程执行的。可以写更新UI的代码。（工作线程是不能更新UI的）
      mDelivery.post(new Runnable() {
        @Override public void run() {
          if (callback != null) {
              callback.onFailure(e);
          }
        }
      });
  }
  public void sendSuccessCallBack(final ResultCallback callback,final Object o) {
    mDelivery.post(new Runnable() {
      @Override public void run() {
        if (callback != null) {
            callback.onSuccess(o);
        }
      }
    });
  }
  /**********************对外暴露一个接口************************/

  //get请求
  public static void get(String url,ResultCallback resultCallback) {
    getmInstance().getRequest(url,resultCallback);
  }
  //http请求回调类，回调方法在UI线程中执行
  public static abstract class ResultCallback<T> {
    Type mType;
    public ResultCallback(){
      mType = getSuperclassTypeParameter(getClass());
    }
    static Type getSuperclassTypeParameter(Class<?> subclass) {
     /* * getClass().getGenericSuperclass()返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type
      * ，然后将其转ParameterizedType。
        getActualTypeArguments()返回表示此类型实际类型参数的 Type 对象的数组。
        [0]就是这个数组中第一个了，简而言之就是获得超类的泛型参数的实际类型。
      * */
      Type superclass = subclass.getGenericSuperclass();
      if (subclass instanceof Class) {
          throw new RuntimeException("Missing type parameter.");
      }
      ParameterizedType parameterizedType = (ParameterizedType)superclass;
      return $Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
    }
    //请求成功的回调
      public abstract void onSuccess(T response);
    //请求失败的回调
      public abstract void onFailure(Exception e);
  }
  //post请求参数类
  public static class Param{

    String value;
    String key;

    public Param(){}

    public Param(String key, String value) {
      this.key = key;
      this.value = value;
    }
  }
}





