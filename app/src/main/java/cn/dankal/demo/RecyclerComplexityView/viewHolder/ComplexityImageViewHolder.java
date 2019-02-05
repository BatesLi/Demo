package cn.dankal.demo.RecyclerComplexityView.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;

public class ComplexityImageViewHolder extends RecyclerView.ViewHolder {

  public @BindView(R.id.img_complexity_item) ImageView mImgComplexityItem;

  public ComplexityImageViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}

