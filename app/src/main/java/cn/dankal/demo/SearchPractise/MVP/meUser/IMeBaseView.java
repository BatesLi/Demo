package cn.dankal.demo.SearchPractise.MVP.meUser;

public interface IMeBaseView {
    //显示等待框
    void showLoading(String msg);

    //隐藏等待框
    void hideLoading();

    //错误信息
    void showError(String error);
}
