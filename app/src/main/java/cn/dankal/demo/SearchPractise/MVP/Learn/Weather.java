package cn.dankal.demo.SearchPractise.MVP.Learn;

/**
 * @author
 * @date
 * @org
 * @email
 * @describe 有一种短信服务，比如天气预报服务，一旦你订阅该服务
 * ，你只需按月付费，付完费后，每天一旦有天气信息更新，它就会及时向你发送最新的天气信息。
 * observer | observable
 * 被观察者 类似是adapter，因为一旦数据源发生改变，被观察者就会通知观察者 我变了
 */

public class Weather {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "weather{" + "description='" + description + '\'' + '}';
    }
}
