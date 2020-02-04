package cn.dankal.demo.bateCallbackInterface;

public class SingleTonB {

   /* public SingleTonCallback mCallback;

    public SingleTonB(SingleTonCallback callback) {
        this.mCallback = callback;
        抽象:不是B方法里面的这个方法，而是方法里面的回调这个行为
        A类的A方法只有在B类的B方法运行完毕之后才能接着运行，有一个问题就是
        ，当B类的B方法是耗时任务应该怎么办
    }*/

    public void eatB(SingleTonCallback callback) {
        //通知A，我运行完毕了
        callback.callback(1);
    }
}
