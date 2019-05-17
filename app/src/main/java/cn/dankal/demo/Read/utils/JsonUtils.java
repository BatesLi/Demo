package cn.dankal.demo.Read.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Type;

public class JsonUtils {

  private static Gson mGson = new Gson();
  //将对象转化为json字符串
  public static<T> String serialize(T object) {
    return mGson.toJson(object);
  }
  //将json对象转成实体对象
  public static <T> T deserialize(JsonObject json,Class<T> clz) throws JsonSyntaxException {
    return mGson.fromJson(json, clz);
  }
  //将json字符串转为对象
  public static <T> T deserialize(String json,Class<T> clz) throws JsonSyntaxException {
    return mGson.fromJson(json,clz);
  }
  /**
   * 将json字符串转换为对象
   */
  public static <T> T deserialize(String json, Type type) throws JsonSyntaxException {
    return mGson.fromJson(json, type);
  }
}
