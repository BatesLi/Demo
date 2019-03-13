package cn.dankal.demo.RetrofitRxjavaEventBus.utlis;

public class ApiException extends RuntimeException {

  public static final int USER_NOT_EXIST = 100;
  public static final int WRONG_PASSWORD = 101;

  public ApiException(int resultCode) {
    this(getApiExceptionMessage(resultCode));
  }

  public ApiException(String detailMessage) {
    super(detailMessage);
  }

  private static String getApiExceptionMessage(int code) {
    String message = "";
    switch (code) {
      case USER_NOT_EXIST:
        message = "用户不存在";
        break;
      case WRONG_PASSWORD:
        message = "密码错误";
        break;
      default:
        message = "未知错误";
    }
    return message;
  }
}
