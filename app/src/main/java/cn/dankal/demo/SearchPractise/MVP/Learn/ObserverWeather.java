package cn.dankal.demo.SearchPractise.MVP.Learn;

//观察者只需要实现一个观察者的接口observer，该接口也是泛型的。
//一旦订阅的主题发生变换就会回调该接口。
public interface ObserverWeather<T> {
    void onUpData(ObservableWeather<T> observableWeather, T data);
}
