package cn.dankal.demo.SearchPractise.MVP;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.dankal.basic_lib.util.ToastUtil;
import cn.dankal.demo.R;
import cn.dankal.demo.SearchPractise.MVP.contact.MvpIView;
import cn.dankal.demo.SearchPractise.MVP.contact.MvpPresenter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MvpActivity extends AppCompatActivity implements MvpIView {

    public MvpPresenter mMvpPresenter;
    @BindView(R.id.edit_network_url)
    EditText mEditNetworkUrl;
    @BindView(R.id.btn_network_request)
    Button mBtnNetworkRequest;
    @BindView(R.id.txt_mvp_request)
    TextView mTxtMvpRequest;
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 0:
                    mTxtMvpRequest.setText(message.obj.toString());
                    break;
            }
        }
    };
    @BindView(R.id.btn_observer)
    Button mBtnObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        ButterKnife.bind(this);

        mMvpPresenter = new MvpPresenter(this);
    }

    @OnClick(R.id.btn_network_request)
    public void OnClickBtnRequest(View view) {
        switch (view.getId()) {
            case R.id.btn_network_request:
                mMvpPresenter.getDataUrl(mEditNetworkUrl.getText().toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void setData(String rul) {
        Message message = mHandler.obtainMessage(0, rul);
        mHandler.sendMessage(message);
    }

    @Override
    public void error() {

    }
}
