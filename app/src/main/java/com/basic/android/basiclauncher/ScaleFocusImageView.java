package com.basic.android.basiclauncher;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ScaleFocusImageView extends ConstraintLayout {

    /* renamed from: p */
    public TextView f3913p;

    /* renamed from: q */
    public float f3914q = getResources().getFraction(R.fraction.home_app_banner_focused_scale, 1, 1);

    public ScaleFocusImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f3913p = (TextView) findViewById(R.id.text_view_icon_title);
    }

    public void onFocusChanged(boolean z, int i, Rect rect) {
        int i2;
        TextView textView;
        super.onFocusChanged(z, i, rect);
        if (!z) {
            animate().z(0.0f).scaleX(1.0f).scaleY(1.0f).setDuration(150);
            textView = this.f3913p;
            i2 = 8;
        } else {
            animate().z(2.0f).scaleX(this.f3914q).scaleY(this.f3914q).setDuration(150);
            textView = this.f3913p;
            i2 = 0;
        }
        textView.setVisibility(i2);
    }
}
