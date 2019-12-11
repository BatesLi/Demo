package cn.dankal.demo.SearchPractise.MVP.user;

public class UserPresenter implements IUserPresenter {

    public IUserView mIUserView;
    public UserModel mUserModel;

    public UserPresenter(IUserView view) {
        this.mIUserView = view;
    }

    @Override
    public void doLogin(String name, String password) {
        mUserModel = new UserModel(name, password);
        boolean rec = false;
        int code = mUserModel.checkLoginResult();
        if (code == 0) {
            rec = true;
        }
        mIUserView.doLoginResult(rec, code);
    }
}

