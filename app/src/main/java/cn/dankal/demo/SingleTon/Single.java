package cn.dankal.demo.SingleTon;

public class Single {

  private static Single single = new Single();

  //私有化构造函数
  private Single() {
  }

  public static Single getSingle() {
    return single;
  }
}
