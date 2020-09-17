package com.basic.android.basiclauncher.notifications;

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
    
    public final float zoom;
    public ImageView imageViewBack;
    public ImageView imageViewFront;
    public Context context;
    public TextView textView;

    public NotificationsPanelButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Resources resources = context.getResources();
        this.context = context;
        this.zoom = resources.getFraction(R.fraction.app_banner_focused_scale, 1, 1);
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        imageViewBack = findViewById(R.id.ivNotificationBackground);
        imageViewFront = findViewById(R.id.ivNotificationIcon);
        this.textView = findViewById(R.id.notification_panel_button_text);
        imageViewBack.setClipToOutline(true);
    }

    public void onFocusChanged(boolean z, int i, Rect rect) {
        float f;
        ViewPropertyAnimator viewPropertyAnimator;
        super.onFocusChanged(z, i, rect);
        Drawable white = ContextCompat.getDrawable(getContext(), R.drawable.hollow_circle_background);
        Drawable black = ContextCompat.getDrawable(getContext(), R.drawable.hollow_circle_background_black);
        Drawable drawable2 = ContextCompat.getDrawable(getContext(), R.drawable.full_circle_background);

        if (!z) {
            imageViewBack.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
            imageViewFront.setImageDrawable(white);
            f = 1.0f;
            viewPropertyAnimator = imageViewBack.animate().z(0.0f).scaleX(1.0f);
        } else {
            imageViewBack.setVisibility(View.VISIBLE);
            textView.setText("Notification");
            textView.setVisibility(View.VISIBLE);
            imageViewFront.setImageDrawable(black);
            imageViewBack.setImageDrawable(drawable2);
            viewPropertyAnimator = imageViewBack.animate().z(-2.0f).scaleX(this.zoom);
            f = zoom;
        }
        viewPropertyAnimator.scaleY(f).setDuration(150);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 109 || i == 23 || i == 66) {
            try {
                this.context.startActivity(new Intent("com.android.tv.NOTIFICATIONS_PANEL"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            this.context.startActivity(new Intent("com.android.tv.NOTIFICATIONS_PANEL"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onTouchEvent(motionEvent);
    }
}
