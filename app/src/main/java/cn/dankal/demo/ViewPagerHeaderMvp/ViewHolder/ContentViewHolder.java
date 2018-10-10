package cn.dankal.demo.ViewPagerHeaderMvp.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;

public class ContentViewHolder extends RecyclerView.ViewHolder {

  public @BindView(R.id.txt_news_show) TextView mTxtShowNews;

  public ContentViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
