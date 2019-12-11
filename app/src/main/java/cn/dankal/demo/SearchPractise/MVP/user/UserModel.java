package cn.dankal.demo.SearchPractise.MVP.user;

public class UserModel implements IUserModel {

    private String name;
    private String password;

    public UserModel(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String getUserName() {
        return name;
    }

    @Override
    public String getUserPassword() {
        return password;
    }

    @Override
    public int checkLoginResult() {
        if (name.equals("kenan") && password.equals("123456")) {
            return 0;
        } else {
            return -1;
        }
    }
}
