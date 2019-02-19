package cn.dankal.demo.multi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import cn.dankal.demo.R;
import cn.dankal.demo.multi.viewHolder.MultiViewHolderOne;
import cn.dankal.demo.multi.viewHolder.MultiViewHolderThree;
import cn.dankal.demo.multi.viewHolder.MultiViewHolderTwo;
import java.util.List;

public class MultiAdapter extends RecyclerView.Adapter {

  public static final int ITEM_ONE = 0;
  public static final int ITEM_TWO = 1;
  public static final int ITEM_THREE = 2;

  public List<Integer> mMultiBeans;
  public Context mContext;

  public MultiAdapter(List<Integer> multiBeans, Context context) {
    this.mMultiBeans = multiBeans;
    this.mContext = context;
  }

  @NonNull @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if (viewType == ITEM_ONE) {
      return new MultiViewHolderOne(LayoutInflater.from(mContext).inflate(R.layout.item_multi_one
          , parent, false));
    } else if (viewType == ITEM_TWO) {
      return new MultiViewHolderTwo(LayoutInflater.from(mContext).inflate(R.layout.item_multi_two
          , parent, false));
    } else {
      return new MultiViewHolderThree(
          LayoutInflater.from(mContext).inflate(R.layout.item_multi_three
              , parent, false));
    }
  }

  @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof MultiViewHolderOne) {
      //((MultiViewHolderOne) holder).mTxtOne.setText(mMultiBeans.get(position).getTitle());
    }
  }

  @Override public int getItemCount() {
    return mMultiBeans.size();
  }

  @Override
  public int getItemViewType(int position) {
    if (position < 3) {
      return ITEM_ONE;
    } else if (position < 6) {
      return ITEM_TWO;
    } else {
      return ITEM_THREE;
    }
  }
}
