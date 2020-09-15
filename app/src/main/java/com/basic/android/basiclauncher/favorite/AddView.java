package com.basic.android.basiclauncher.favorite;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.basic.android.basiclauncher.R;
import com.basic.android.basiclauncher.view.AddActivity;

public class AddView extends LinearLayout {

    /* renamed from: c */
    public  Context context;

    /* renamed from: a */
    public final float f3916a = getResources().getFraction(R.fraction.app_banner_focused_scale, 1, 1);

    /* renamed from: b */
    public TextView f3917b;

    public AddView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f3917b = findViewById(R.id.app_add_title);
    }

    public void onFocusChanged(boolean z, int i, Rect rect) {
        ViewPropertyAnimator viewPropertyAnimator;
        float f;
        super.onFocusChanged(z, i, rect);
        if (!z) {
            this.f3917b.setVisibility(View.GONE);
            f = 1.0f;
            viewPropertyAnimator = animate().z(0.0f).scaleX(1.0f);
        } else {
            this.f3917b.setVisibility(View.VISIBLE);
            this.f3917b.setText("Add/Remove");
            viewPropertyAnimator = animate().z(0.0f).scaleX(this.f3916a);
            f = this.f3916a;
        }
        viewPropertyAnimator.scaleY(f).setDuration(150);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 109 || i == 23 || i == 66) {
            Log.d("Opening", "open hard");
            context.startActivity(new Intent(context, AddActivity.class));
        }
        return super.onKeyUp(i, keyEvent);
    }
}
