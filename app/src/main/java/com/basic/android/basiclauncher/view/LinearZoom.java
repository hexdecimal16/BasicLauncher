package com.basic.android.basiclauncher.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewPropertyAnimator;
import android.widget.LinearLayout;

import com.basic.android.basiclauncher.R;

public class LinearZoom extends LinearLayout {

    /* renamed from: a */
    public final float zoom = getResources().getFraction(R.fraction.home_app_banner_focused_scale, 1, 1);

    public LinearZoom(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
    }

    public void onFocusChanged(boolean z, int i, Rect rect) {
        ViewPropertyAnimator viewPropertyAnimator;
        float f;
        super.onFocusChanged(z, i, rect);
        if (!z) {
            f = 1.0f;
            viewPropertyAnimator = animate().z(0.0f).scaleX(1.0f);
        } else {
            viewPropertyAnimator = animate().z(0.0f).scaleX(this.zoom);
            f = this.zoom;
        }
        viewPropertyAnimator.scaleY(f).setDuration(150);
    }
}
