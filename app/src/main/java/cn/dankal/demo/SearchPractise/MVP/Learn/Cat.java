package cn.dankal.demo.SearchPractise.MVP.Learn;

public class Cat {
    //私有构造函数 | 创建类的静态实例 | 提供一个对外获取实例的公开静态函数

    private static volatile Cat sCat;

    private Cat() {
    }

    public static Cat getInstance() {
        if (sCat == null) {
            synchronized (Cat.class) {
                if (sCat == null) {
                    sCat = new Cat();
                }
            }
        }
        return sCat;
    }
}
