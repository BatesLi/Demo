package cn.dankal.demo.recyclerMoreView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.dankal.demo.R;
import cn.dankal.demo.recyclerMoreView.ViewHolder.OneViewHolder;
import cn.dankal.demo.recyclerMoreView.ViewHolder.ThreeViewHolder;
import cn.dankal.demo.recyclerMoreView.ViewHolder.TwoViewHolder;
import java.util.List;

public class RecyclerMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  public static final int ITEM_ONE = 0;
  public static final int ITEM_TWO = 1;
  public static final int ITEM_THREE = 2;

  public List<MoreData> mMoreData;
  public Context mContext;

  public RecyclerMoreAdapter(Context context, List<MoreData> moreData) {
    this.mContext = context;
    this.mMoreData = moreData;
  }

  @NonNull @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view;
    switch (viewType) {
      case ITEM_ONE:
        view = LayoutInflater.from(mContext).inflate(R.layout.item_one, parent, false);
        OneViewHolder oneViewHolder = new OneViewHolder(view);
        return oneViewHolder;
      case ITEM_TWO:
        view = LayoutInflater.from(mContext).inflate(R.layout.item_two, parent, false);
        TwoViewHolder twoViewHolder = new TwoViewHolder(view);
        return twoViewHolder;
      case ITEM_THREE:
        view = LayoutInflater.from(mContext).inflate(R.layout.item_three, parent, false);
        ThreeViewHolder threeViewHolder = new ThreeViewHolder(view);
        return threeViewHolder;
      default:
        return null;
    }
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    MoreData moreData = mMoreData.get(position);
    switch (moreData.getViewType()) {
      case ITEM_ONE:
        break;
      case ITEM_TWO:
        break;
      case ITEM_THREE:
        ThreeViewHolder threeViewHolder = (ThreeViewHolder) holder;
        threeViewHolder.mTxtThree.setText(mMoreData.get(position).getTitle());
    }
  }

  @Override
  public int getItemCount() {
    return mMoreData != null ? mMoreData.size() : 0;
  }

  @Override
  public int getItemViewType(int position) {
    return mMoreData.get(position).getViewType();
  }
}
