package cn.dankal.demo.SearchPractise.MVP.utility;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class JsonUtils {
    private static Gson mGson = new Gson();

    //将对象转为字符
    public static <T> String serialize(T object) {
        return mGson.toJson(object);
    }

    //讲json字符串转为对象
    public static <T> T deserialize(String json, Class<T> clz) throws JsonSyntaxException {
        return mGson.fromJson(json, clz);
    }
}
