package cn.dankal.demo.SearchPractise.MVP.Learn;

import rx.Observable;
import rx.Subscriber;

public class Main {

    public static void main(String[] args) {
        ObservableWeather<Weather> observableWeather = new ObservableWeather<>();
        ObserverWeather<Weather> observerWeather = new ObserverWeather<Weather>() {
            @Override
            public void onUpData(ObservableWeather<Weather> observableWeather, Weather data) {
                System.out.println("Main.onUpData :  " + data.getDescription());
            }
        };

        observableWeather.register(observerWeather);

        Weather weather = new Weather();
        weather.setDescription("多雨大风");
        observableWeather.notifyObserver(weather);

        //创建一个被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("upData");
                subscriber.onCompleted();
            }
        });
        //创建一个观察者
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("Main.onNext:   " + s);
            }
        };
        //观察者进行事件的订阅
        observable.subscribe(subscriber);

    }
}
