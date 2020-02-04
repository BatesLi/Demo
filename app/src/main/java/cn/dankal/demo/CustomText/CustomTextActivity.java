package cn.dankal.demo.CustomText;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.basic_lib.util.ToastUtil;
import cn.dankal.demo.R;

public class CustomTextActivity extends AppCompatActivity {

    @BindView(R.id.txt_custom)
    TextView mTxtCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_custom);
        ButterKnife.bind(this);
    }
}
