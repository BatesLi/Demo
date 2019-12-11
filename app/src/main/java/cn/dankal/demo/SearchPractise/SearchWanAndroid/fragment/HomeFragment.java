package cn.dankal.demo.SearchPractise.SearchWanAndroid.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.dankal.basic_lib.util.ToastUtil;
import cn.dankal.demo.R;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.Api.HomeContact;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date
 * @org
 * @email
 * @describe
 */

public class HomeFragment extends Fragment {

    public List<String> mPhotos;
    public HomeContact.HomePresenter mHomePresenter;
    @BindView(R.id.banner_home_photo)
    XBanner mBannerHomePhoto;
    @BindViews({R.id.txt_home_navigation, R.id.txt_home_article, R.id.txt_home_project,
            R.id.txt_home_pwd, R.id.txt_top_stick, R.id.txt_top_more, R.id.txt_top_article
            , R.id.txt_top_back})
    List<TextView> mTextViews;
    @BindView(R.id.recycler_home_top_article)
    RecyclerView mRecyclerHomeArticle;
    @BindView(R.id.recycler_home_back)
    RecyclerView mRecyclerHomeBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_home, container, false);
        ButterKnife.bind(this, view);
        //接口引用指向 一个对象（实现该接口的对象）
        initBanner();
        return view;
    }

    @OnClick({R.id.txt_home_navigation, R.id.txt_home_article, R.id.txt_home_project,
            R.id.txt_home_pwd, R.id.txt_top_stick, R.id.txt_top_more, R.id.txt_top_article
            , R.id.txt_top_back})
    public void onClickTxtHome(View view) {
        switch (view.getId()) {
            case R.id.txt_home_navigation:
                ToastUtil.toToast("navigation");
                break;
            case R.id.txt_home_article:
                ToastUtil.toToast("article");
                break;
            case R.id.txt_home_project:
                ToastUtil.toToast("project");
                break;
            case R.id.txt_home_pwd:
                ToastUtil.toToast("pwd");
                break;
            case R.id.txt_top_stick:
                ToastUtil.toToast("txt_top_stick");
                break;
            case R.id.txt_top_more:
                ToastUtil.toToast("top_more");
                break;
            default:
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mBannerHomePhoto.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBannerHomePhoto.stopAutoPlay();
    }

    public void initBanner() {
        mPhotos = new ArrayList<>();
        mPhotos.add("https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png");
        mPhotos.add("https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png");
        mPhotos.add("https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png");

        mBannerHomePhoto.setData(mPhotos, null);
        mBannerHomePhoto.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(view.getContext())
                        .load(mPhotos.get(position))
                        .placeholder(R.drawable.default_image)
                        .error(R.drawable.default_image)
                        .into((ImageView) view);
            }
        });
    }
}
