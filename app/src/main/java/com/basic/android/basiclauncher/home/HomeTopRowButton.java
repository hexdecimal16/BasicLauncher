package com.basic.android.basiclauncher.home;

import android.animation.AnimatorSet;
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
    public View view;
    public ImageView imageView;
    public Context context;
    public TextView textView;

    public HomeTopRowButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        Resources resources = getResources();
        this.zoom = resources.getFraction(R.fraction.app_banner_focused_scale, 1, 1);
        resources.getColor(R.color.reference_white_100, (Resources.Theme) null);
        resources.getColor(R.color.reference_white_60, (Resources.Theme) null);
        resources.getInteger(R.integer.top_row_button_animation_duration_ms);
        new AnimatorSet();
        new AnimatorSet();
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.imageView = (ImageView) findViewById(R.id.button_icon);
        View findViewById = findViewById(R.id.button_background);
        this.textView = (TextView) findViewById(R.id.settings_title);
        this.view = findViewById;
        this.imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_action_settings_white));
    }

    public void onFocusChanged(boolean z, int i, Rect rect) {
        float f;
        ViewPropertyAnimator viewPropertyAnimator;
        super.onFocusChanged(z, i, rect);
        Drawable black = ContextCompat.getDrawable(getContext(), R.drawable.ic_action_settings_black);
        Drawable white = ContextCompat.getDrawable(getContext(), R.drawable.ic_action_settings_white);
        if (!z) {
            this.imageView.setBackground(white);
            this.imageView.setVisibility(View.VISIBLE);
            this.textView.setVisibility(View.GONE);
            this.view.setVisibility(View.GONE);
            f = 1.0f;
            viewPropertyAnimator = animate().z(0.0f).scaleX(1.0f);
        } else {
            this.view.setVisibility(View.VISIBLE);
            this.textView.setText("Settings");
            this.textView.setVisibility(View.VISIBLE);
            Drawable drawable2 = ContextCompat.getDrawable(getContext(), R.drawable.full_circle_background);
            this.view.setForeground(black);
            this.view.setBackground(drawable2);
            this.imageView.setVisibility(View.INVISIBLE);
            viewPropertyAnimator = animate().z(0.0f).scaleX(this.zoom);
            f = this.zoom;
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
