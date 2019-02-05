package cn.dankal.demo.RecyclerComplexityView.bean;

import java.util.List;

public class BeanComplexityData {

  private String image_head;
  private String image_designer;
  private String image_video;

  private List<BeanComplexityImage> beanImageList;
  private List<BeanComplexityProduct> productList;
  private List<BeanComplexityOther> otherList;

  public BeanComplexityData() {
  }

  public BeanComplexityData(String image_head, String image_designer, String image_video,
      List<BeanComplexityImage> beanImageList, List<BeanComplexityProduct> productList,
      List<BeanComplexityOther> otherList) {
    this.image_head = image_head;
    this.image_designer = image_designer;
    this.image_video = image_video;
    this.beanImageList = beanImageList;
    this.productList = productList;
    this.otherList = otherList;
  }

  public String getImage_head() {
    return image_head;
  }

  public void setImage_head(String image_head) {
    this.image_head = image_head;
  }

  public String getImage_designer() {
    return image_designer;
  }

  public void setImage_designer(String image_designer) {
    this.image_designer = image_designer;
  }

  public String getImage_video() {
    return image_video;
  }

  public void setImage_video(String image_video) {
    this.image_video = image_video;
  }

  public List<BeanComplexityImage> getBeanImageList() {
    return beanImageList;
  }

  public void setBeanImageList(
      List<BeanComplexityImage> beanImageList) {
    this.beanImageList = beanImageList;
  }

  public List<BeanComplexityProduct> getProductList() {
    return productList;
  }

  public void setProductList(
      List<BeanComplexityProduct> productList) {
    this.productList = productList;
  }

  public List<BeanComplexityOther> getOtherList() {
    return otherList;
  }

  public void setOtherList(
      List<BeanComplexityOther> otherList) {
    this.otherList = otherList;
  }
}
