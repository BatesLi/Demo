package cn.dankal.demo.SearchPractise.MVP.contact;

import cn.dankal.basic_lib.util.ToastUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MvpPresenter {

    public MvpIView mIView;
    public MvpModel mMvpModel;

    public MvpPresenter(MvpIView IView) {
        mMvpModel = new MvpModel();
        this.mIView = IView;
    }

    public void getDataUrl(String url) {

        int http = url.indexOf("http");
        int https = url.indexOf("https");

        if (http != -1 && https != -1) {
            mMvpModel.getData(url).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    mIView.error();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    mIView.setData(response.body().string());
                }
            });
        } else {
            ToastUtil.toToast("请输入以http或https为首的正确网络地址");
        }

    }
}

