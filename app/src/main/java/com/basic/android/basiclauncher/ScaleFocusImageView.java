package com.basic.android.basiclauncher;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ScaleFocusImageView extends ConstraintLayout {

    public TextView textView;
    public float zoom = getResources().getFraction(R.fraction.home_app_banner_focused_scale, 1, 1);

    public ScaleFocusImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.textView = (TextView) findViewById(R.id.text_view_icon_title);
    }

    public void onFocusChanged(boolean z, int i, Rect rect) {
        int i2;
        TextView textView;
        super.onFocusChanged(z, i, rect);
        if (!z) {
            animate().z(0.0f).scaleX(1.0f).scaleY(1.0f).setDuration(150);
            textView = this.textView;
            i2 = 8;
        } else {
            animate().z(2.0f).scaleX(this.zoom).scaleY(this.zoom).setDuration(150);
            textView = this.textView;
            i2 = 0;
        }
        textView.setVisibility(i2);
    }
}
