package com.basic.android.basiclauncher.notifications;

import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
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


public class NotificationsPanelButtonView extends LinearLayout {

    /* renamed from: a */
    public final float f3923a;

    /* renamed from: b */
    public TextView notification_count;

    /* renamed from: c */
    public Drawable f3925c;

    /* renamed from: d */
    public View f3926d;

    /* renamed from: e */
    public TextView f3927e;

    /* renamed from: f */
    public final int f3928f;

    /* renamed from: g */
    public Drawable f3929g;

    /* renamed from: h */
    public ImageView f3930h;

    /* renamed from: i */
    public final int f3931i;

    /* renamed from: j */
    public final int f3932j;

    /* renamed from: k */
    public int f3933k;

    /* renamed from: l */
    public int f3934l;

    /* renamed from: m */
    public final int f3935m;

    /* renamed from: n */
    public Context f3936n;

    /* renamed from: o */
    public TextView f3937o;

    public NotificationsPanelButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Resources resources = context.getResources();
        this.f3936n = context;
        this.f3923a = resources.getFraction(R.fraction.app_banner_focused_scale, 1, 1);
        this.f3931i = resources.getColor(R.color.white_60, null);
        this.f3932j = resources.getColor(R.color.notification_panel_icon_focused_color, null);
        this.f3933k = resources.getColor(R.color.notification_panel_icon_unseen_color, null);
        this.f3928f = resources.getColor(R.color.reference_white_100, null);
        this.f3934l = resources.getColor(R.color.notification_panel_icon_text_unfocused_color, null);
        this.f3935m = resources.getInteger(R.integer.top_row_button_animation_duration_ms);
        resources.getString(R.string.number_format);
        resources.getString(R.string.greater_than_nine_notifs_text);
        resources.getDimension(R.dimen.text_size_h4);
        resources.getDimension(R.dimen.text_size_h5);
        Drawable drawable = resources.getDrawable(R.drawable.hollow_circle_background, null);
        this.f3925c = drawable;
        drawable.setTint(this.f3931i);
        this.f3929g = resources.getDrawable(R.drawable.full_circle_background, null);
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f3937o = findViewById(R.id.notification_panel_button_text);
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.f3926d = findViewById(R.id.button_background);
        ImageView imageView = findViewById(R.id.notification_panel_background_circle);
        this.f3930h = imageView;
        imageView.setImageDrawable(this.f3925c);
        this.notification_count = findViewById(R.id.notification_panel_count);
        this.f3927e = findViewById(R.id.button_title);
        this.f3926d.setClipToOutline(true);
        ObjectAnimator.ofFloat(this.f3927e, "alpha", 0.0f, 1.0f).setDuration(this.f3935m);
        ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(this.f3931i), Integer.valueOf(this.f3932j)}).setDuration(this.f3935m);
        ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(this.f3932j), Integer.valueOf(this.f3931i)}).setDuration(this.f3935m);
        ObjectAnimator.ofArgb(this.f3929g, "tint", this.f3933k, this.f3932j).setDuration(this.f3935m);
        ObjectAnimator.ofArgb(this.f3929g, "tint", this.f3932j, this.f3933k).setDuration(this.f3935m);
        ObjectAnimator.ofArgb(this.notification_count, "textColor", this.f3934l, this.f3928f).setDuration(this.f3935m);
        ObjectAnimator.ofArgb(this.notification_count, "textColor", this.f3928f, this.f3934l).setDuration(this.f3935m);
        AnimatorInflater.loadAnimator(getContext(), R.animator.expand_fade_in).setTarget(this.f3926d);
        AnimatorInflater.loadAnimator(getContext(), R.animator.shrink_fade_out).setTarget(this.f3926d);
    }

    public void onFocusChanged(boolean z, int i, Rect rect) {
        float f;
        ViewPropertyAnimator viewPropertyAnimator;
        super.onFocusChanged(z, i, rect);
        Drawable white = ContextCompat.getDrawable(getContext(), R.drawable.hollow_circle_background);
        Drawable black = ContextCompat.getDrawable(getContext(), R.drawable.hollow_circle_background_black);

        if (!z) {
            this.f3930h.setVisibility(View.VISIBLE);
            this.f3926d.setVisibility(View.GONE);
            this.f3937o.setVisibility(View.GONE);
            f = 1.0f;
            viewPropertyAnimator = animate().z(0.0f).scaleX(1.0f);
        } else {
            this.f3926d.setVisibility(View.VISIBLE);
            this.f3937o.setText("Notification");
            this.f3937o.setVisibility(View.VISIBLE);
            Drawable drawable2 = ContextCompat.getDrawable(getContext(), R.drawable.full_circle_background);
            this.f3926d.setForeground(black);
            this.f3926d.setBackground(drawable2);
            this.f3930h.setVisibility(View.INVISIBLE);
            viewPropertyAnimator = animate().z(0.0f).scaleX(this.f3923a);
            f = this.f3923a;
        }
        viewPropertyAnimator.scaleY(f).setDuration(150);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 109 || i == 23 || i == 66) {
            try {
                this.f3936n.startActivity(new Intent("com.android.tv.NOTIFICATIONS_PANEL"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            this.f3936n.startActivity(new Intent("com.android.tv.NOTIFICATIONS_PANEL"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onTouchEvent(motionEvent);
    }
}
