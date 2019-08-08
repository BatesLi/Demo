package cn.dankal.demo.SearchPractise.SearchWanAndroid.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import butterknife.BindColor;
import cn.dankal.demo.R;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.Utils.StringUtils;
import cn.dankal.demo.SearchPractise.SearchWanAndroid.model.SearchResult;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;

public class SearchResultAdapter
    extends BaseQuickAdapter<SearchResult.DataBean.Datas, BaseViewHolder> {

  @BindColor(R.color.bg_rv_item_tv) int bg_rv_item_tv;

  private int type;

  public SearchResultAdapter(int type, @Nullable List<SearchResult.DataBean.Datas> data) {
    super(type, data);
    this.type = type;
  }

  public SearchResultAdapter(int type, int layoutResId,
      @Nullable List<SearchResult.DataBean.Datas> data) {
    super(layoutResId, data);
    this.type = type;
  }

  @Override protected void convert(BaseViewHolder helper, SearchResult.DataBean.Datas item) {
    switch (type) {
      case StringUtils.RV_ITEM_IMG:
        if (TextUtils.isEmpty(item.getTitle())) {
          return;
        } else {
          if (TextUtils.isEmpty(item.getAuthor())) {
            return;
          } else {
            /*
            *     .replace("&1dquo;","\"")
                  .replace("&rdquo;","\"")
                  .replace("&mdash;","——")
            *
            * */
            helper.setText(R.id.txt_article_title, item.getTitle()
                .replace("<em class='highlight'>", "")
                .replace("</em>", ""));
            helper.setText(R.id.txt_article_author, item.getAuthor());
            helper.setText(R.id.txt_article_super_chapter, item.getSuperChapterName());
            helper.setText(R.id.txt_article_chapter, "/" + item.getChapterName());
            helper.setText(R.id.txt_article_date, item.getNiceDate());
            helper.setText(R.id.txt_article_chapter_title, "分类：");
            helper.setText(R.id.txt_article_author_title, "作者：");

            helper.setBackgroundColor(R.id.txt_article_title, 0);
            helper.setBackgroundColor(R.id.txt_article_author, 0);
            helper.setBackgroundColor(R.id.txt_article_super_chapter, 0);
            helper.setBackgroundColor(R.id.txt_article_chapter, 0);
            helper.setBackgroundColor(R.id.txt_article_date, 0);
            helper.setBackgroundColor(R.id.txt_article_chapter_title, 0);
            helper.setBackgroundColor(R.id.txt_article_author_title, 0);

            helper.addOnClickListener(R.id.img_btn_article_save);
            if (item.isCollect()) {

            }
          }
        }
        break;
    }
  }
}
