package cn.dankal.demo.multi.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;

public class MultiViewHolderOne extends RecyclerView.ViewHolder {

  public @BindView(R.id.img_one) ImageView mImgOne;
  public @BindView(R.id.txt_one) TextView mTxtOne;

  public MultiViewHolderOne(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
