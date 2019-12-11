package cn.dankal.demo.SearchPractise.SearchWanAndroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import cn.dankal.demo.R;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.model.Data;

import java.util.List;

public class HomeFragmentTopStickAdapter extends RecyclerView.Adapter<HomeFragmentTopStickAdapter.HomeTopStickViewHolder> {

    public List<Data> mDataList;
    public Context mContext;

    public HomeFragmentTopStickAdapter(List<Data> dataList, Context context) {
        this.mDataList = dataList;
        mContext = context;
    }

    @NonNull
    @Override
    public HomeTopStickViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_article, parent, false);
        HomeTopStickViewHolder holder = new HomeTopStickViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeTopStickViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class HomeTopStickViewHolder extends RecyclerView.ViewHolder {

        public HomeTopStickViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
