package cn.dankal.demo.SearchPractise.MVP.GitHub;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;
import cn.dankal.demo.SearchPractise.MVP.GitHub.adapter.GitHubUserAdapter;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.dankal.demo.SearchPractise.MVP.GitHub.ui.IndicateDotView;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class GitHubActivity extends AppCompatActivity {

    public List<HubUser> mDataList;
    public GitHubUserAdapter mAdapter;
    @BindView(R.id.recycler_git_hub)
    RecyclerView mRecyclerGitHub;
    @BindView(R.id.avl_loading)
    AVLoadingIndicatorView mAvlLoading;
    //@BindView(R.id.loading_circle) LoadingCircle mLoadingCircle;
    @BindView(R.id.indicate_view_circle)
    IndicateDotView mIndicateDotView;
    @BindView(R.id.img_circle_loading_one)
    ImageView mImgCircleLoadingOne;
    @BindView(R.id.img_circle_loading_two)
    ImageView mImgCircleLoadingTwo;
    @BindView(R.id.img_circle_loading_three)
    ImageView mImgCircleLoadingThree;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_hub);
        ButterKnife.bind(this);

        mDataList = new ArrayList<>();
        initData("ezmobius");
        mRecyclerGitHub.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new GitHubUserAdapter(this, mDataList);
        mRecyclerGitHub.setAdapter(mAdapter);
    }

    public void initData(String userName) {
        mAvlLoading.setVisibility(View.VISIBLE);
        GitHubService service = GitHubApi.createService(GitHubService.class);
        //平行发送多个请求，最后的结果统一返回到UI线程
        Subscription subscription = service.getUserFollowingObservable(userName)
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<List<HubUser>, Observable<HubUser>>() {
                    @Override
                    public Observable<HubUser> call(List<HubUser> hubUsers) {
                        return Observable.from(hubUsers);
                    }
                })
                .flatMap(new Func1<HubUser, Observable<HubUser>>() {
                    @Override
                    public Observable<HubUser> call(HubUser user) {
                        return service.getUserObservable(user.getLogin())
                                .subscribeOn(Schedulers.io());
                    }
                })
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<HubUser>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<HubUser> hubUsers) {
                        mDataList.addAll(hubUsers);
                        if (mDataList.size() != 0) {
                            mAvlLoading.setVisibility(View.GONE);
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });
        compositeSubscription.add(subscription);
    }

    @Override
    public void onPause() {
        super.onPause();
        compositeSubscription.unsubscribe();
    }
}
