package cn.dankal.demo.SearchPractise.MVP.Learn;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date
 * @org
 * @email
 * @describe 被观察者需要提供register和unRegister方法给观察者订阅与取消订阅 ?
 * 我认为就只需要 notifyObserver一个方法,作为数据更新 通知观察者。
 * 1:注册的方法可以用于判断 观察者的行为。*它是否null *它什么时候添加到集合中
 * 观察者可以有很多，所以写成一个list集合：数据源需要能复用，所以用T作为通用类来写。
 * 订阅的行为如何 用code表述?
 */
public class ObservableWeather<T> {

    List<ObserverWeather<T>> mObserverWeathers = new ArrayList<>();

    public void register(ObserverWeather<T> observerWeather) {
        if (observerWeather == null) {
            throw new NullPointerException("pointer == null");
        }
        synchronized (this) {
            if (!mObserverWeathers.contains(observerWeather)) {
                mObserverWeathers.add(observerWeather);
            }
        }
    }

    public void unregister(ObserverWeather<T> observerWeather) {
        mObserverWeathers.remove(observerWeather);
    }

    //通知观察者，我更新数据了，你也要及时跟进哦
    public void notifyObserver(T data) {
        for (ObserverWeather<T> observerWeather : mObserverWeathers) {
            observerWeather.onUpData(this, data);//通过接口传值： 更新后的值
        }
    }
}
