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

    /* renamed from: a */
    public final float f3918a;

    /* renamed from: b */
    public View f3919b;

    /* renamed from: c */
    public ImageView f3920c;

    /* renamed from: d */
    public Context context;

    /* renamed from: e */
    public TextView f3922e;

    public HomeTopRowButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        Resources resources = getResources();
        this.f3918a = resources.getFraction(R.fraction.app_banner_focused_scale, 1, 1);
        resources.getColor(R.color.reference_white_100, (Resources.Theme) null);
        resources.getColor(R.color.reference_white_60, (Resources.Theme) null);
        resources.getInteger(R.integer.top_row_button_animation_duration_ms);
        new AnimatorSet();
        new AnimatorSet();
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        Resources resources = getResources();
        this.f3920c = (ImageView) findViewById(R.id.button_icon);
        TextView textView = (TextView) findViewById(R.id.button_title);
        View findViewById = findViewById(R.id.button_background);
        this.f3922e = (TextView) findViewById(R.id.settings_title);
        this.f3919b = findViewById;
        this.f3920c.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_action_settings_white));
    }

    public void onFocusChanged(boolean z, int i, Rect rect) {
        float f;
        ViewPropertyAnimator viewPropertyAnimator;
        super.onFocusChanged(z, i, rect);
        Resources resources = getResources();
        Drawable black = ContextCompat.getDrawable(getContext(), R.drawable.ic_action_settings_black);
        Drawable white = ContextCompat.getDrawable(getContext(), R.drawable.ic_action_settings_white);
        if (!z) {
            this.f3920c.setBackground(white);
            this.f3920c.setVisibility(View.VISIBLE);
            this.f3922e.setVisibility(View.GONE);
            this.f3919b.setVisibility(View.GONE);
            f = 1.0f;
            viewPropertyAnimator = animate().z(0.0f).scaleX(1.0f);
        } else {
            this.f3919b.setVisibility(View.VISIBLE);
            this.f3922e.setText("Settings");
            this.f3922e.setVisibility(View.VISIBLE);
            Drawable drawable2 = ContextCompat.getDrawable(getContext(), R.drawable.full_circle_background);
            this.f3919b.setForeground(black);
            this.f3919b.setBackground(drawable2);
            this.f3920c.setVisibility(View.INVISIBLE);
            viewPropertyAnimator = animate().z(0.0f).scaleX(this.f3918a);
            f = this.f3918a;
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
