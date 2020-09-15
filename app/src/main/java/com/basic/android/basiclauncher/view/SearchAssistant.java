package com.basic.android.basiclauncher.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.basic.android.basiclauncher.R;


public class SearchAssistant extends FrameLayout {

    private static float zoom;
    private ImageView imageView;
    private View view;

    public SearchAssistant(Context context) {
        super(context);
        init();
    }

    public SearchAssistant(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchAssistant(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Resources resources = getResources();
        zoom = resources.getFraction(R.fraction.app_banner_focused_scale, 1, 1);
        imageView = findViewById(R.id.mic);
        view = findViewById(R.id.button_background_mic);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        init();
        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean b) {
                SearchView.changeFocus(b);
                ViewPropertyAnimator viewPropertyAnimator;
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_mic_color);
                Drawable drawable2 = ContextCompat.getDrawable(getContext(), R.drawable.ic_google_assistant);
                float f;
                if (!b) {
                    imageView.setVisibility(View.VISIBLE);
                    view.setVisibility(View.GONE);
                    imageView.setBackground(drawable2);
                    f = 1.0f;
                    viewPropertyAnimator = SearchAssistant.this.animate().z(0.0f).scaleX(1.0f);
                } else {
                    imageView.setVisibility(View.GONE);
                    view.setVisibility(View.VISIBLE);
                    Drawable drawable3 = ContextCompat.getDrawable(getContext(), R.drawable.full_circle_background);
                    view.setForeground(drawable);
                    view.setBackground(drawable3);
                    viewPropertyAnimator = SearchAssistant.this.animate().z(0.0f).scaleX(zoom);
                    f = zoom;
                }
                viewPropertyAnimator.scaleY(f).setDuration(150);
            }
        });
    }

    @Override
    protected void onFocusChanged(boolean z, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(z, direction, previouslyFocusedRect);

    }

}


