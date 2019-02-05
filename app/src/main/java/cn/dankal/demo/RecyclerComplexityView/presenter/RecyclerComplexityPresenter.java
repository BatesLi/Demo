package cn.dankal.demo.RecyclerComplexityView.presenter;

import android.support.annotation.NonNull;
import cn.dankal.demo.RecyclerComplexityView.api.BeanComplexityDataApi;
import cn.dankal.demo.RecyclerComplexityView.api.BeanComplexityImageApi;
import cn.dankal.demo.RecyclerComplexityView.api.BeanComplexityOtherApi;
import cn.dankal.demo.RecyclerComplexityView.api.BeanComplexityProductApi;
import cn.dankal.demo.RecyclerComplexityView.bean.BeanComplexityProduct;
import cn.dankal.demo.RecyclerComplexityView.contact.RecyclerComplexityContact;
import java.util.ArrayList;
import java.util.List;

public class RecyclerComplexityPresenter
    implements RecyclerComplexityContact.RecyclerComplexityIPresenter {

  RecyclerComplexityContact.RecyclerComplexityIView iView;

  @Override public void requestJsonComplexity() {
    List<Object> data = new ArrayList<>();
    data.add(BeanComplexityDataApi.getData());
    data.add(BeanComplexityDataApi.getData());
    //这里应该是一个list的数据集，展示多张图
    data.addAll(BeanComplexityImageApi.getBeanImageList()); //数据的处理不懂
    //添加商品list
    for (int i = 0; i < BeanComplexityProductApi.getProductList().size(); i++) {
      BeanComplexityProduct product = BeanComplexityProductApi.getProductList().get(i);
      data.addAll(product.getBeanProductList());
    }
    //添加other的数据
    data.addAll(BeanComplexityOtherApi.getOtherList());
    iView.getComplexityData(data);
  }

  @Override
  public void attachView(@NonNull RecyclerComplexityContact.RecyclerComplexityIView view) {
    this.iView = view;
  }
}
