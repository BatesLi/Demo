package cn.dankal.demo.RetrofitRxjavaEventBus.utlis;

public interface SubscriberOnNextListener<T> {
  void onNext(T t);
}
