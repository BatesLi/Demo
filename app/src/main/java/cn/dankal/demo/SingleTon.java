package cn.dankal.demo;

public class SingleTon {

    /*
     * 1：构造方法私有化
     * 2：单例类自己创建自己唯一的实例
     * 3：创建一个返回值是单例类的公开静态方法
     * */

    private static SingleTon sSingleTon = null;

    private SingleTon() {
    }

    public static SingleTon getInstance() {
        if (sSingleTon == null) {
            synchronized (SingleTon.class) {
                if (sSingleTon == null) {
                    sSingleTon = new SingleTon();
                }
            }
        }
        return sSingleTon;
    }
}
