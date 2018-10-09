package cn.dankal.demo.mvp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;

public class BlankFragment extends Fragment {

  @BindView(R.id.txt_show) TextView mTxtShow;

  @Override
  public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup,
      Bundle savedInstanceState) {
    View view = layoutInflater.inflate(R.layout.fragment_blank, viewGroup, false);
    ButterKnife.bind(this, view);
    return view;
  }
}
