package cn.dankal.demo.SearchPractise.MVP.GitHub;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

public interface GitHubService {
    // Using RxJava
    @GET("users/{user}")
    Observable<HubUser> getUserObservable(@Path("user") String user);

    @GET("users/{user}/following")
    Observable<List<HubUser>> getUserFollowingObservable(@Path("user") String user);
}
