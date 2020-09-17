package com.basic.android.basiclauncher.home;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.basic.android.basiclauncher.R;

public class HomeTopRowButton extends LinearLayout {

    public final float zoom;
    public ImageView imageViewBack;
    public ImageView imageViewFront;
    public Context context;
    public TextView textView;

    public HomeTopRowButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        Resources resources = getResources();
        this.zoom = resources.getFraction(R.fraction.app_banner_focused_scale, 1, 1);
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        imageViewFront = findViewById(R.id.ivSettingsIcon);
        imageViewBack = findViewById(R.id.ivSettingsBackground);
        textView = findViewById(R.id.settings_title);
        imageViewFront.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_action_settings_white));
    }

    public void onFocusChanged(boolean z, int i, Rect rect) {
        float f;
        ViewPropertyAnimator viewPropertyAnimator;
        super.onFocusChanged(z, i, rect);
        Drawable black = ContextCompat.getDrawable(getContext(), R.drawable.ic_action_settings_black);
        Drawable white = ContextCompat.getDrawable(getContext(), R.drawable.ic_action_settings_white);
        Drawable drawable2 = ContextCompat.getDrawable(getContext(), R.drawable.full_circle_background);
        if (!z) {
            imageViewFront.setImageDrawable(white);
            textView.setVisibility(View.GONE);
            imageViewBack.setVisibility(View.GONE);
            f = 1.0f;
            viewPropertyAnimator = imageViewBack.animate().z(0.0f).scaleX(1.0f);
        } else {
            imageViewBack.setVisibility(View.VISIBLE);
            textView.setText("Settings");
            textView.setVisibility(View.VISIBLE);
            imageViewFront.setImageDrawable(black);
            imageViewBack.setImageDrawable(drawable2);
            viewPropertyAnimator = imageViewBack.animate().z(-2.0f).scaleX(zoom);
            f = zoom;
        }
        viewPropertyAnimator.scaleY(f).setDuration(150);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 109 || i == 23 || i == 66) {
            try {
                this.context.startActivity(new Intent("android.settings.SETTINGS"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            this.context.startActivity(new Intent("android.settings.SETTINGS"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onTouchEvent(motionEvent);
    }
}
