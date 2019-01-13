package cn.dankal.demo.recyclerMoreView.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;

public class TwoViewHolder extends RecyclerView.ViewHolder {
  @BindView(R.id.view_two) View mViewTwo;

  public TwoViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
