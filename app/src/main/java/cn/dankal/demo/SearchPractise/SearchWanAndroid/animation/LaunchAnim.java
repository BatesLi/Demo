package cn.dankal.demo.SearchPractise.SearchWanAndroid.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

public class LaunchAnim {

    private static LaunchAnim INSTANCE;

    public LaunchAnim() {
    }

    public static LaunchAnim getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new LaunchAnim();
        }
        return INSTANCE;
    }

    public static void showLogo(final View... views) {
        if (views.length != 0) {
            ObjectAnimator rotation = ObjectAnimator.ofFloat(views[0], "Rotation", 0, 180);
            ObjectAnimator rotationReverse = ObjectAnimator.ofFloat(views[0], "Rotation", 180, 0);
            rotation.setRepeatMode(ValueAnimator.REVERSE);
            rotationReverse.setRepeatMode(ValueAnimator.REVERSE);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(2000).playTogether(rotation, rotationReverse);
            animatorSet.start();

            showMain(views[0], views[1], views[2]);
        }
    }

    public static void showMain(View... views) {
        for (int i = 0; i < views.length; i++) {
            views[i].setVisibility(View.VISIBLE);
        }
    }
}
