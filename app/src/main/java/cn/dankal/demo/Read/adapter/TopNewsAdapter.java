package cn.dankal.demo.Read.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import cn.dankal.demo.R;
import cn.dankal.demo.Read.bean.NewsListBean;
import cn.dankal.demo.Read.utils.DensityUtils;
import cn.dankal.demo.Read.utils.GlideUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;

public class TopNewsAdapter extends BaseQuickAdapter<NewsListBean.NewsBean, BaseViewHolder> {

  public TopNewsAdapter(@Nullable List<NewsListBean.NewsBean> data) {
    super(R.layout.item_top_news);
  }

  @Override protected void convert(BaseViewHolder helper, NewsListBean.NewsBean item) {
    if (helper.getPosition() % 2 == 0) {
      DensityUtils.setViewMargin(helper.itemView,false,0,0,0,40);
    }else {
      DensityUtils.setViewMargin(helper.itemView, false, 5, 0, 0, 40);
    }
    helper.setText(R.id.tv_item_top_news,item.getTitle());
    GlideUtils.loadImage(3,item.getImgsrc(),helper.getView(R.id.iv_item_top_news));
    //Recycler的item点击事件
    helper.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onItemClickListener.onItemClickListener(item.getDocid(),
            item.getImgsrc(),helper.getView(R.id.iv_item_top_news));
      }
    });
  }
  OnItemClickListener onItemClickListener;
  public interface OnItemClickListener{
    void onItemClickListener(String id,String imgUrl,View view);
  }
  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }
}
