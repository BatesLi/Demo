package cn.dankal.demo.RecyclerComplexityView.contact;

import cn.dankal.demo.RecyclerComplexityView.base.BaseComplexityIPresenter;
import cn.dankal.demo.RecyclerComplexityView.base.BaseComplexityIView;
import java.util.List;

public interface RecyclerComplexityContact {
  interface RecyclerComplexityIView extends BaseComplexityIView {
    void getComplexityData(List<Object> objectList);
  }

  interface RecyclerComplexityIPresenter extends BaseComplexityIPresenter<RecyclerComplexityIView> {
    void requestJsonComplexity();
  }
}
