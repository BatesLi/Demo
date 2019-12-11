package cn.dankal.demo.SearchPractise.MVP.meUser;

import cn.dankal.demo.SearchPractise.MVP.user.IUserPresenter;

public class MeUserPresenter extends MeBaseUserPresenter<IMeUserView> implements IUserPresenter {

    public MeUserModel mMeUserModel;
    public IMeUserView mIMeUserView;

    public MeUserPresenter() {
        mMeUserModel = new MeUserModel();
    }

    @Override
    public void doLogin(String name, String password) {
        //M层是获取数据的，V层是展示UI逻辑的，P层是展示业务逻辑的，在MVP中M与V是解耦且没有联系的。
        mIMeUserView = getMeBaseUserView();
        if (name.length() == 0 && password.length() == 0) {
            mIMeUserView.showToast("账号或者密码不能为空");
        } else {
            mIMeUserView.showLoading("正在登录");
            mMeUserModel.getLogin(name, password, new callBack() {
                @Override
                public void Success() {
                    mIMeUserView.hideLoading();
                    mIMeUserView.showResult("登录成功");
                }

                @Override
                public void onFailure(String message) {
                    mIMeUserView.hideLoading();
                    mIMeUserView.showError(message);
                }
            });
        }
    }
}
