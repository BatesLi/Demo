package cn.dankal.demo.RetrofitRxjavaEventBus.utlis;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

public class ProgressDialogHandler extends Handler {

  public static final int SHOW_PROGRESS_DIALOG = 1;
  public static final int DISMISS_PROGRESS_DIALOG = 2;

  private ProgressDialog progressDialog;
  private Context context;
  private ProgressCancelListener progressCancelListener;
  private boolean cancelable;

  public ProgressDialogHandler(Context context
      , ProgressCancelListener progressCancelListener, boolean cancelable) {
    super();
    this.cancelable = cancelable;
    this.context = context;
    this.progressCancelListener = progressCancelListener;
  }

  private void initProgressDialog() {
    if (progressDialog == null) {
      progressDialog = new ProgressDialog(context);
      progressDialog.setCancelable(cancelable);

      if (cancelable) {
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
          @Override public void onCancel(DialogInterface dialog) {
            progressCancelListener.onCancelProgress();
          }
        });
      }
      if (progressDialog.isShowing()) {
        progressDialog.show();
      }
    }
  }

  private void dismissProgressDialog() {
    if (progressDialog != null) {
      progressDialog.dismiss();
      progressDialog = null;
    }
  }

  @Override
  public void handleMessage(Message message) {
    switch (message.what) {
      case SHOW_PROGRESS_DIALOG:
        initProgressDialog();
        break;
      case DISMISS_PROGRESS_DIALOG:
        dismissProgressDialog();
        break;
    }
  }
}
