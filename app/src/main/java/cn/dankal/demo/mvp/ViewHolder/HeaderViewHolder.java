package cn.dankal.demo.mvp.ViewHolder;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;

public class HeaderViewHolder extends RecyclerView.ViewHolder {

  public @BindView(R.id.vp_header) ViewPager mVpHeader;
  public @BindView(R.id.ll_hottest_indicator) LinearLayout mLlHottestIndicator;

  public HeaderViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
