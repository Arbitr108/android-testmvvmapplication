package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.smartdeveloper.ru.testrxmvvmapplication.R;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class LayoutWatcher implements View.OnLayoutChangeListener {
    private static final String TAG = "LayoutWatcher";

    @Override
    public void onLayoutChange(View view, int left, int top, int right, int bottom,
                               int oldLeft, int oldTop, int oldRight, int oldBottom) {
        final int oldHeight = oldBottom - oldTop;
        final int newHeight = bottom - top;
        if(newHeight != oldHeight){
            ImageView photo = view.findViewById(R.id.photo);
            ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(photo, "scaleX", 0.2f);
            ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(photo, "scaleY", 0.2f);
            scaleDownX.setDuration(1000);
            scaleDownY.setDuration(1000);

            AnimatorSet scaleDown = new AnimatorSet();
            scaleDown.play(scaleDownX).with(scaleDownY);

            scaleDown.start();
        }

        //Log.d(TAG, String.format("onLayoutChange: %d,  %d,  %d,  %d,", left, top, right, bottom));
    }
}
