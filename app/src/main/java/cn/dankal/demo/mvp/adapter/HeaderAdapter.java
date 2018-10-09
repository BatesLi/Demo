package cn.dankal.demo.mvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import cn.dankal.demo.R;
import cn.dankal.demo.mvp.ViewHolder.ContentViewHolder;
import cn.dankal.demo.mvp.ViewHolder.HeaderViewHolder;

public class HeaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int ITEM_TYPE_HEADER = 0;
  private static final int ITEM_TYPE_CONTENT = 1;

  public String[] data = {"java", "python", "C++", "Php", ".NET", "js", "Ruby", "Swift", "OC"};
  private LayoutInflater mLayoutInflater;
  private Context mContext;
  private int mHeaderCount = 1; //头部view个数

  public HeaderAdapter(Context context) {
    this.mContext = context;
    mLayoutInflater = LayoutInflater.from(context);
  }

  public int getContentItemCount() {
    return data.length;
  }

  public boolean isHeaderView(int position) {
    return mHeaderCount != 0 && position < mHeaderCount;
  }

  @Override
  public int getItemViewType(int position) {
    int dataItemCount = getContentItemCount();
    if (mHeaderCount != 0 && position < mHeaderCount) {
      return ITEM_TYPE_HEADER;
    } else {
      return ITEM_TYPE_CONTENT;
    }
  }

  @NonNull @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if (viewType == ITEM_TYPE_HEADER) {
      return new HeaderViewHolder(
          mLayoutInflater.inflate(R.layout.item_news_header, parent, false));
    } else if (viewType == ITEM_TYPE_CONTENT) {
      return new ContentViewHolder(mLayoutInflater.inflate(R.layout.item_news, parent, false));
    }
    return null;
  }

  @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof HeaderViewHolder) {

    } else if (holder instanceof ContentViewHolder) {

    }
  }

  @Override public int getItemCount() {
    return mHeaderCount + getContentItemCount();
  }
}
