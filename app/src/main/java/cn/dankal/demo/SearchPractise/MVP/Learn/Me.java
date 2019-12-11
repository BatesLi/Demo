package cn.dankal.demo.SearchPractise.MVP.Learn;

public class Me {
    private ObserverWeather<Weather> mObserverWeather;

    public Me(ObserverWeather<Weather> observerWeather) {
        this.mObserverWeather = observerWeather;
        showMe();
    }

    public void showMe() {
        ObservableWeather<Weather> observableWeather = new ObservableWeather<>();
        Weather weather = new Weather();
        weather.setDescription("阴转多云");
        observableWeather.notifyObserver(weather);
        observableWeather.register(mObserverWeather);

        mObserverWeather.onUpData(observableWeather, weather);
    }
}
