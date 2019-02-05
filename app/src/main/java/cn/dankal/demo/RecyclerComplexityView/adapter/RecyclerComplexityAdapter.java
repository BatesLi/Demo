package cn.dankal.demo.RecyclerComplexityView.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import cn.dankal.demo.R;
import cn.dankal.demo.RecyclerComplexityView.api.BeanComplexityDataApi;
import cn.dankal.demo.RecyclerComplexityView.bean.BeanComplexityData;
import cn.dankal.demo.RecyclerComplexityView.bean.BeanComplexityImage;
import cn.dankal.demo.RecyclerComplexityView.bean.BeanComplexityOther;
import cn.dankal.demo.RecyclerComplexityView.bean.BeanComplexityProduct;
import cn.dankal.demo.RecyclerComplexityView.viewHolder.ComplexityDesignViewHolder;
import cn.dankal.demo.RecyclerComplexityView.viewHolder.ComplexityHeadViewHolder;
import cn.dankal.demo.RecyclerComplexityView.viewHolder.ComplexityImageViewHolder;
import cn.dankal.demo.RecyclerComplexityView.viewHolder.ComplexityOtherViewHolder;
import cn.dankal.demo.RecyclerComplexityView.viewHolder.ComplexityProductHeaderViewHolder;
import cn.dankal.demo.RecyclerComplexityView.viewHolder.ComplexityProductViewHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;

public class RecyclerComplexityAdapter extends RecyclerView.Adapter {

  //头部
  public static final int ITEM_HEAD = 0;
  //设计师的信息
  public static final int ITEM_MESSAGE = 1;
  //图片
  public static final int ITEM_IMAGE = 2;
  //商品头部
  public static final int ITEM_PRODUCT_HEAD = 3;
  //商品
  public static final int ITEM_PRODUCT = 4;
  //其他系列
  public static final int ITEM_OTHER = 5;

  public Context mContext;
  public List<Object> mData;
  // protected RequestOptions mRequestOptionsCircle; //设置glide的圆形图片的类

  public RecyclerComplexityAdapter(Context context, List<Object> data) {
    this.mContext = context;
    this.mData = data;
  }

  @Override
  public int getItemViewType(int position) {
    if (position == 0) {
      return ITEM_HEAD;
    } else if (position == 1) {
      return ITEM_MESSAGE;
    } else if (mData.get(position) instanceof BeanComplexityImage) { //什么意思(首先通过get方法获取集合的位置，之后)
      return ITEM_IMAGE;
    } else if (mData.get(position) instanceof BeanComplexityProduct.BeanData) {
      return ITEM_PRODUCT;
    } else if (mData.get(position) instanceof BeanComplexityOther) {
      return ITEM_OTHER;
    } else {
      return ITEM_PRODUCT_HEAD;//商品头部
    }
  }

  @NonNull @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    if (viewType == ITEM_HEAD) {
      return new ComplexityHeadViewHolder(LayoutInflater.from(mContext)
          .inflate(R.layout.item_complexity_head, parent, false));
    } else if (viewType == ITEM_MESSAGE) {
      return new ComplexityDesignViewHolder(LayoutInflater.from(mContext)
          .inflate(R.layout.item_complexity_design, parent, false));
    } else if (viewType == ITEM_IMAGE) {
      return new ComplexityImageViewHolder(LayoutInflater.from(mContext)
          .inflate(R.layout.item_complexity_image, parent, false));
    } else if (viewType == ITEM_PRODUCT_HEAD) {
      return new ComplexityProductHeaderViewHolder(LayoutInflater.from(parent.getContext())
          .inflate(R.layout.item_complexity_product_header, parent, false));
    } else if (viewType == ITEM_PRODUCT) {
      return new ComplexityProductViewHolder(LayoutInflater.from(mContext)
          .inflate(R.layout.item_complexity_product, parent, false));
    } else if (viewType == ITEM_OTHER) {
      return new ComplexityOtherViewHolder(LayoutInflater.from(mContext)
          .inflate(R.layout.item_complexity_other, parent, false));
    } else {
      return null;
    }
  }

  @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof ComplexityHeadViewHolder) {
      BeanComplexityData beanComplexityData = (BeanComplexityData) mData.get(position);
      Glide.with(mContext)
          .load(beanComplexityData.getImage_head())
          .into(((ComplexityHeadViewHolder) holder).mImgComplexityHead);
    } else if (holder instanceof ComplexityDesignViewHolder) {
      BeanComplexityData beanComplexityData = BeanComplexityDataApi.getData();
      Glide.with(mContext)
          .load(beanComplexityData.getImage_designer())
          .apply(RequestOptions.bitmapTransform(new CircleCrop())) //设置圆形头像
          .into(((ComplexityDesignViewHolder) holder).mImgCircle);
    } else if (holder instanceof ComplexityImageViewHolder) {
      BeanComplexityImage image = (BeanComplexityImage) mData.get(position);
      Glide.with(mContext)
          .load(image.getImage())
          .into(((ComplexityImageViewHolder) holder).mImgComplexityItem);
      //商品头部
    } else if (holder instanceof ComplexityProductHeaderViewHolder) {
      ((ComplexityProductHeaderViewHolder) holder).mTxtProductHead.setText(
          (String) mData.get(position));
    } else if (holder instanceof ComplexityProductViewHolder) {
      BeanComplexityProduct.BeanData product = (BeanComplexityProduct.BeanData) mData.get(position);
      ((ComplexityProductViewHolder) holder).mTxtCommodity_name.setText(product.getName());
      ((ComplexityProductViewHolder) holder).mTxtPrice.setText(product.getPrice());
      Glide.with(mContext)
          .load(product.getImage())
          .into(((ComplexityProductViewHolder) holder).mImgProduct);
    } else if (holder instanceof ComplexityOtherViewHolder) {
      BeanComplexityOther other = (BeanComplexityOther) mData.get(position);
      ((ComplexityOtherViewHolder) holder).mTxtOtherName.setText(other.getName());
      Glide.with(mContext)
          .load(other.getImage())
          .into(((ComplexityOtherViewHolder) holder).mImageOther);
    }
  }

  @Override public int getItemCount() {
    return mData.size();
  }
  //设置glide的圆形图片
  /*private RequestOptions getInstance() {
    mRequestOptionsCircle = new RequestOptions()
        .error(R.mipmap.item1)
        .placeholder(R.mipmap.item2)
        .transform(new RequestOptionsCircle()); //圆形头像
    return mRequestOptionsCircle;
  }*/
 /* //添加数据
  public void addData(BeanComplexityData beanComplexityData) {
    mData = new ArrayList<>();
    mData.add(beanComplexityData);
    mData.add(beanComplexityData);
    mData.addAll(beanComplexityData.getBeanImageList());
  }*/
}
