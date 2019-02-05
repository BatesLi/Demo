package cn.dankal.demo.RecyclerComplexityView.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;

public class ComplexityProductHeaderViewHolder extends RecyclerView.ViewHolder {

  public @BindView(R.id.txt_product_head) TextView mTxtProductHead;

  public ComplexityProductHeaderViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
