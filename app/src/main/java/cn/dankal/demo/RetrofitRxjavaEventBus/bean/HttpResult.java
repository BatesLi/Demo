package cn.dankal.demo.RetrofitRxjavaEventBus.bean;

//对服务端返回结果进行统一处理
public class HttpResult<T> {

  private int resultCode;
  private String resultMessage;
  //不确定后台服务器返回的 数据类型 是什么,用通配符 T 替代
  private T data;

  public int getResultCode() {
    return resultCode;
  }

  public void setResultCode(int resultCode) {
    this.resultCode = resultCode;
  }

  public String getResultMessage() {
    return resultMessage;
  }

  public void setResultMessage(String resultMessage) {
    this.resultMessage = resultMessage;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
