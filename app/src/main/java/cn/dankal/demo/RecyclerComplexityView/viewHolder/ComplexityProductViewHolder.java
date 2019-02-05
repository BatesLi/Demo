package cn.dankal.demo.RecyclerComplexityView.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;

public class ComplexityProductViewHolder extends RecyclerView.ViewHolder {

  public @BindView(R.id.img_product) ImageView mImgProduct;
  public @BindView(R.id.txt_commodity_name) TextView mTxtCommodity_name;
  public @BindView(R.id.txt_price) TextView mTxtPrice;

  public ComplexityProductViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
