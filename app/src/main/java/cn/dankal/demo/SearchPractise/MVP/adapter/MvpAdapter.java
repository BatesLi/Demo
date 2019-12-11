package cn.dankal.demo.SearchPractise.MVP.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;
import cn.dankal.demo.SearchPractise.MVP.base.MvpData;

import java.util.List;

public class MvpAdapter extends RecyclerView.Adapter<MvpAdapter.MvpViewHolder> {

    public List<MvpData.DataBean> mDataBeanList;
    public Context mContext;

    public MvpAdapter(Context context, List<MvpData.DataBean> dataBeanList) {
        this.mContext = context;
        this.mDataBeanList = dataBeanList;
    }

    @NonNull
    @Override
    public MvpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_request_mvp, parent, false);
        return new MvpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MvpViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDataBeanList.size();
    }

    public class MvpViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_name_mvp)
        TextView mTxtNameMvp;

        public MvpViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
