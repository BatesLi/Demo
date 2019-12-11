package cn.dankal.demo.SearchPractise.SearchWanAndroid.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import cn.dankal.demo.R;

public class TitleAnim {

    private static TitleAnim INSTANCE;

    public TitleAnim() {
    }

    public static TitleAnim getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TitleAnim();
        }
        return INSTANCE;
    }

    public static void showAnimTitle(View view, View imgBtn, String text, int page) {

        TextView textView = (TextView) view;
        ImageButton imageButton = (ImageButton) imgBtn;
        textView.setText(text);

        ObjectAnimator alpha_title = ObjectAnimator.ofFloat(textView, "alpha", 0, 1);
        ObjectAnimator alpha_imgBtn = ObjectAnimator.ofFloat(imageButton, "alpha", 0, 1);
        switch (page) {
            case 0:
                imageButton.setVisibility(View.VISIBLE);
                imageButton.setImageResource(R.mipmap.search);
                break;
            case 1:
            case 2:
                if (imageButton.getAlpha() != 0 || imageButton.getVisibility() == View.VISIBLE) {
                    alpha_imgBtn = ObjectAnimator.ofFloat(imageButton, "alpha", 1, 0);
                }
                break;
            case 3:
                alpha_imgBtn = ObjectAnimator.ofFloat(imageButton, "alpha", 0, 1);
                imageButton.setImageResource(R.mipmap.setting);
                break;
            default:
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500).playTogether(alpha_title, alpha_imgBtn);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (page == 1 || page == 2) {
                    if (imageButton.getVisibility() != View.GONE) {
                        imageButton.setVisibility(View.GONE);
                    }
                }
                if (page == 0 || page == 3) {
                    if (imageButton.getVisibility() != View.VISIBLE) {
                        imageButton.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    public static void hideAnimTitle(View... views) {
        ObjectAnimator hideImg = ObjectAnimator.ofFloat(views[0], "alpha", 0);
        hideImg.setDuration(2000).start();
    }

    /**
     * 文字的渐隐
     */
    public static void hide(View... views) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(views, "alpha", 0, 1);
        objectAnimator.setDuration(500).start();
    }

    public static void hide(View view) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
        objectAnimator.setDuration(500).start();
    }
}
