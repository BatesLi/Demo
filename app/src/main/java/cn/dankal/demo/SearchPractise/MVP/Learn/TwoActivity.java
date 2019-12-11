package cn.dankal.demo.SearchPractise.MVP.Learn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;

import org.greenrobot.eventbus.EventBus;

public class TwoActivity extends AppCompatActivity {

    @BindView(R.id.btn_two)
    Button mBtnTwo;
    @BindView(R.id.edit_input_message)
    EditText mEditInputMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        ButterKnife.bind(this);

        mBtnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEditInputMessage.getText().toString())) {
                    Toast.makeText(v.getContext(), "请输入.......", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    EventBus.getDefault().post(new MessageEvent(mEditInputMessage.getText().toString()));
                    finish();
                }
            }
        });
    }
}
