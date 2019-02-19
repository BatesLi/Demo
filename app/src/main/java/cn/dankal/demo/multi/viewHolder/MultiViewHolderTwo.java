package cn.dankal.demo.multi.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;

public class MultiViewHolderTwo extends RecyclerView.ViewHolder {

  @BindView(R.id.img_two) ImageView mImgTwo;
  @BindView(R.id.txt_two) TextView mTxtTwo;

  public MultiViewHolderTwo(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
