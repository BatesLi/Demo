package cn.dankal.basic_lib.util;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

public class ToastUtil {
    public static Toast toast;

    private static Context mContext;

    public static void register(Context context) {
        mContext = context.getApplicationContext();
    }


    public static void toToast(String str) {
      if (str == null || str.isEmpty()) {
        return;
      }
        if (toast == null) {
            toast = Toast.makeText(mContext, str, Toast.LENGTH_SHORT);
        } else {
            toast.setText(str);
        }
        toast.show();
    }

    public static void toToast(@StringRes int Rid) {
        Toast.makeText(mContext, mContext.getResources().getString(Rid), Toast.LENGTH_SHORT).show();
    }

    public static void cancle() {
        if (toast != null)
            toast.cancel();
    }

    public static void toLongToast(String str) {
        if (toast == null) {
            toast = Toast.makeText(mContext, str, Toast.LENGTH_LONG);
        } else {
            toast.setText(str);
        }
        toast.show();
    }

    public static void toToast2StringRes(int Rid) {
        Toast.makeText(mContext, mContext.getResources().getString(Rid), Toast.LENGTH_LONG).show();
    }

}
