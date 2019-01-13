package cn.dankal.demo.recyclerMoreView.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;

public class OneViewHolder extends RecyclerView.ViewHolder {
  @BindView(R.id.img_one) ImageView mImgOne;

  public OneViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
