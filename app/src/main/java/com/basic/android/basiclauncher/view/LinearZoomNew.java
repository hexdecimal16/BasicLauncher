package com.basic.android.basiclauncher.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewPropertyAnimator;
import android.widget.LinearLayout;

import com.basic.android.basiclauncher.R;

public class LinearZoomNew extends LinearLayout {

    public final float zoom = getResources().getFraction(R.fraction.home_app_banner_focused_scale, 1, 1);

    public LinearZoomNew(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFocusChanged(boolean z, int i, Rect rect) {
        ViewPropertyAnimator viewPropertyAnimator;
        float f;
        super.onFocusChanged(z, i, rect);
        if (!z) {
            f = 1.0f;
            viewPropertyAnimator = animate().z(0.0f).scaleX(1.0f);
        } else {
            viewPropertyAnimator = animate().z(2.0f).scaleX(this.zoom);
            f = this.zoom;
        }
        viewPropertyAnimator.scaleY(f).setDuration(150);
    }
}
