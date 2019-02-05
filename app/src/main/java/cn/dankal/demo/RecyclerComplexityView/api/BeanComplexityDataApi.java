package cn.dankal.demo.RecyclerComplexityView.api;

import cn.dankal.demo.RecyclerComplexityView.bean.BeanComplexityData;

public class BeanComplexityDataApi {

  public static BeanComplexityData getData() {

    BeanComplexityData beanComplexityData = new BeanComplexityData();

    beanComplexityData.setImage_head(
        "https://images.pexels.com/photos/428124/pexels-photo-428124.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb");
    beanComplexityData.setImage_designer(
        "https://images.pexels.com/photos/428336/pexels-photo-428336.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb");
    beanComplexityData.setImage_video(
        "https://images.pexels.com/photos/428301/pexels-photo-428301.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb");

    beanComplexityData.setBeanImageList(BeanComplexityImageApi.getBeanImageList());
    beanComplexityData.setOtherList(BeanComplexityOtherApi.getOtherList());
    beanComplexityData.setProductList(BeanComplexityProductApi.getProductList());

    return beanComplexityData;
  }
}
