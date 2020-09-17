package com.basic.android.basiclauncher.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.basic.android.basiclauncher.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class LinearZoomNew extends ConstraintLayout {

    public final float zoom = getResources().getFraction(R.fraction.home_app_banner_focused_scale, 1, 1);
    private CircleImageView imageView;
    private TextView bottom;
    private TextView end;

    public LinearZoomNew(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        init();
    }

    private void init() {
        imageView = findViewById(R.id.cardViewRowIcon);
        bottom = findViewById(R.id.textViewBottom);
        end = findViewById(R.id.textViewEnd);
        Drawable drawable = this.getBackground();
        setBackground(null);
        imageView.setBackground(drawable);
    }

    public void onFocusChanged(boolean z, int i, Rect rect) {
        ViewPropertyAnimator viewPropertyAnimator;
        float f;
        super.onFocusChanged(z, i, rect);
        if (!z) {
            end.setVisibility(View.GONE);
            f = 1.0f;
            viewPropertyAnimator = animate().z(0.0f).scaleX(1.0f).withEndAction(new Runnable() {
                @Override
                public void run() {
                    bottom.setVisibility(View.VISIBLE);
                }
            });
        } else {
            bottom.setVisibility(View.GONE);
            end.setVisibility(View.VISIBLE);
            viewPropertyAnimator = animate().z(2.0f).scaleX(this.zoom);
            f = this.zoom;
        }
        viewPropertyAnimator.scaleY(f).setDuration(150);
    }
}
