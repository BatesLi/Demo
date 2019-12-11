package cn.dankal.demo.SearchPractise.MVP.Learn;

public class SingleTon {
    private static SingleTon mSingleTon = null;

    private SingleTon() {
    }

    public static SingleTon getInstance() {
        if (mSingleTon == null) {
            synchronized (SingleTon.class) {
                if (mSingleTon == null) {
                    mSingleTon = new SingleTon();
                    System.out.println("SingleTon.getInstanceOne");
                }
            }
        }
        return mSingleTon;
    }
}
