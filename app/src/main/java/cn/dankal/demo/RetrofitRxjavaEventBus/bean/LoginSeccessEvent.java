package cn.dankal.demo.RetrofitRxjavaEventBus.bean;

public class LoginSeccessEvent {

  private String msg;
  private String username;
  private String password;

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
