package cn.dankal.demo.SearchPractise.MVP.contact;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MvpModel {
    public Call getData(String url) {
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        return httpClient.newCall(request);
    }
}
