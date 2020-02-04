package cn.dankal.demo.bateCallbackInterface;

public class SingleTonA {

    public void eatA() {
        new SingleTonB().eatB(new SingleTonCallback() {
            @Override
            public void callback(int res) {
                System.out.println("返回的结果是 " + res);
            }
        });
        System.out.println("SingleTonA.eatA:  A is running");
    }
}
