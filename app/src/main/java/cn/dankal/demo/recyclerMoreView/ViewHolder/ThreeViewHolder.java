package cn.dankal.demo.recyclerMoreView.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;

public class ThreeViewHolder extends RecyclerView.ViewHolder {

  public @BindView(R.id.txt_three) TextView mTxtThree;

  public ThreeViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
