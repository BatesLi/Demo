package cn.dankal.demo.SearchPractise.MVP.GitHub.Util;

import android.content.Context;
import android.content.res.Resources;

public class DisplayUtil {
    public static int dp2px(Context context, int dp) {
        Resources resources = context.getResources();
        //这个得到的不应该叫做密度，应该是密度的一个比例。不是真实的屏幕密度，而是相对于某个值的屏幕密度。
        float density = resources.getDisplayMetrics().scaledDensity;
        float px = density * dp + 0.5F;
        return (int) px;
    }
}
