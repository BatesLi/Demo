package cn.dankal.demo.RecyclerComplexityView.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;

public class ComplexityHeadViewHolder extends RecyclerView.ViewHolder {

  public @BindView(R.id.img_complexity_head) ImageView mImgComplexityHead;

  public ComplexityHeadViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
