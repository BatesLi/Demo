package cn.dankal.demo.RecyclerComplexityView.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;

public class ComplexityDesignViewHolder extends RecyclerView.ViewHolder {

  public @BindView(R.id.img_circle) ImageView mImgCircle;

  public ComplexityDesignViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
