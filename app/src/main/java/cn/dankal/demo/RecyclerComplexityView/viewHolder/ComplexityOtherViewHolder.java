package cn.dankal.demo.RecyclerComplexityView.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;

public class ComplexityOtherViewHolder extends RecyclerView.ViewHolder {

  public @BindView(R.id.image_other) ImageView mImageOther;
  public @BindView(R.id.txt_other_name) TextView mTxtOtherName;

  public ComplexityOtherViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
