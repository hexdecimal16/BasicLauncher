package com.basic.android.basiclauncher.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.basic.android.basiclauncher.R;

import java.util.Timer;
import java.util.TimerTask;

public class SearchView extends FrameLayout {

    private Context context;
    private TextSwitcher textSwitcher;
    private FrameLayout frameLayout;
    private String[] suggestions;
    
    
    public SearchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Resources resources = getResources();
        this.suggestions = resources.getStringArray(R.array.search_orb_text_to_show);
        this.context = context;
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        init();
        listeners();
        try {
            this.context.getPackageManager().getPackageInfo("com.google.android.googlequicksearchbox", 0);
        } catch (PackageManager.NameNotFoundException unused) {
            unused.printStackTrace();
        }

    }

    private void init() {
        this.textSwitcher = findViewById(R.id.text_switcher);
        this.frameLayout = findViewById(R.id.micLay);
        this.textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(context);
                textView.setText(suggestions[(int) Math.floor(Math.random() * ((double) suggestions.length))]);
                return textView;
            }
        });
        Animation loadAnimation = AnimationUtils.loadAnimation(this.context, R.anim.fade_in);
        loadAnimation.setDuration(1000);
        this.textSwitcher.setInAnimation(loadAnimation);
    }

    private void listeners() {
        this.frameLayout.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i != 109 && i != 23 && i != 66) {
                    return false;
                }
                try {
                    SearchView.this.context.startActivity(new Intent("android.intent.action.ASSIST"));
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            String str = suggestions[(int) Math.floor(Math.random() * ((double) suggestions.length))];
                            textSwitcher.animate();
                            textSwitcher.setText(str);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 6000);
    }
}