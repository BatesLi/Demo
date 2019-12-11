package cn.dankal.demo.SearchPractise.MVP.Learn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.dankal.demo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class OneActivity extends AppCompatActivity {

    @BindView(R.id.btn_one)
    Button mBtnOne;
    @BindView(R.id.txt_show_message)
    TextView mTxtShowMessage;
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            mTxtShowMessage.setText(message.obj.toString());
        }
    };
    @BindView(R.id.btn_start)
    Button mBtnStart;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);

        mBtnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TwoActivity.class);
                startActivity(intent);
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent) {
        mTxtShowMessage.setText(messageEvent.getMsg());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.btn_start})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.obj = "we fright for those cannot";
                        mHandler.sendMessage(message);
                    }
                }).start();
                break;
            default:
                break;
        }
    }
}




