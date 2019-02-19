package cn.dankal.demo.singlePattern;

/*
*
* A. 无论synchronized关键字加在方法上还是对象上，如果它作用的对象是非静态的，则它取得的锁是对象；
* 如果synchronized作用的对象是一个静态方法或一个类，则它取得的锁是对类，该类所有的对象同一把锁。
B. 每个对象只有一个锁（lock）与之相关联，谁拿到这个锁谁就可以运行它所控制的那段代码。
C. 实现同步是要很大的系统开销作为代价的，甚至可能造成死锁，所以尽量避免无谓的同步控制。
* */
public class SecureSingleTon {
 /* //饿汉式
 //饿汉式在类创建的同时就已经创建好一个静态的对象供系统使用，以后不再改变，所以天生是线程安全的。
    private SecureSingleTon(){}
    //private static SecureSingleTon secureSingleTon = new SecureSingleTon();//在类初始化时，已经自行实例化
    public static SecureSingleTon getSecureSingleTon() {
      return secureSingleTon;
  }*/

  /*//饱汉式
  private SecureSingleTon(){}
  private static SecureSingleTon secureSingle = null;

  public static SecureSingleTon getSecureSingle() {
    if (secureSingle == null) {
        secureSingle = new SecureSingleTon();
    }
    return secureSingle;
  }*/
  /*//单例模式：双重检验锁
  private SecureSingleTon(){}
  private static SecureSingleTon secureSingleTon = null;
  public static SecureSingleTon getSecureSingleTon() {
    if (secureSingleTon == null) {
        synchronized (SecureSingleTon.class) {
          if (secureSingleTon == null) {
              secureSingleTon = new SecureSingleTon();
          }
        }
    }
    return secureSingleTon;
  }*/
  //静态内部类实现单例模式
  //创建一个内部类并 在内部类里面创建一个用final修饰的静态外部类对象
  //创建一个返回外部类的方法，并是返回内部类创建的外部类对象。
  private static class LazyHolder {
    private static final SecureSingleTon INSTANCE = new SecureSingleTon();
  }

  private SecureSingleTon() {
  }

  public static final SecureSingleTon getInstance() {
    return LazyHolder.INSTANCE;
  }
}
