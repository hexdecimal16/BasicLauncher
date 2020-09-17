package com.basic.android.basiclauncher.clock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextClock;

import androidx.core.content.res.ResourcesCompat;

import com.basic.android.basiclauncher.R;

public class ClockView extends TextClock {
    public ClockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setFormat12Hour( "K:m" );
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.google_sans);
        setTypeface(typeface);
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

}
