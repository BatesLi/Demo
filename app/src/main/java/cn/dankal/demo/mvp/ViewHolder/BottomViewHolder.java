package cn.dankal.demo.mvp.ViewHolder;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;

public class BottomViewHolder extends RecyclerView.ViewHolder {
  @BindView(R.id.vp_bottom) ViewPager mVpBottom;

  public BottomViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
