package cn.dankal.demo.multi.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;

public class MultiViewHolderThree extends RecyclerView.ViewHolder {

  @BindView(R.id.img_three) ImageView mImgThree;
  @BindView(R.id.txt_three) TextView mTxtThree;

  public MultiViewHolderThree(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
