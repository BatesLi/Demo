package cn.dankal.demo.RecyclerComplexityView.api;

import cn.dankal.demo.RecyclerComplexityView.bean.BeanComplexityOther;
import java.util.ArrayList;
import java.util.List;

public class BeanComplexityOtherApi {

  public static List<BeanComplexityOther> getOtherList() {
    List<BeanComplexityOther> otherList = new ArrayList<>();
    otherList.add(new BeanComplexityOther(
        "https://images.pexels.com/photos/541518/pexels-photo-541518.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
        "xxxxx"));
    otherList.add(new BeanComplexityOther(
        "https://images.pexels.com/photos/518557/pexels-photo-518557.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
        "cccccc"));
    otherList.add(new BeanComplexityOther(
        "https://images.pexels.com/photos/570987/pexels-photo-570987.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
        "ttttt"));
    otherList.add(new BeanComplexityOther(
        "https://images.pexels.com/photos/423367/pexels-photo-423367.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
        "iiiiii"));
    otherList.add(new BeanComplexityOther(
        "https://images.pexels.com/photos/424254/pexels-photo-424254.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
        "ppppppp"));
    otherList.add(new BeanComplexityOther(
        "https://images.pexels.com/photos/435798/pexels-photo-435798.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
        "qqqqq"));
    otherList.add(new BeanComplexityOther(
        "https://images.pexels.com/photos/568785/pexels-photo-568785.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
        "ggggg"));
    otherList.add(new BeanComplexityOther(
        "https://images.pexels.com/photos/437350/pexels-photo-437350.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
        "uuuuuu"));
    otherList.add(new BeanComplexityOther(
        "https://images.pexels.com/photos/437748/pexels-photo-437748.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
        "fffff"));
    return otherList;
  }
}
