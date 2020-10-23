package com.basic.android.basiclauncher.view;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.fonts.FontStyle;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.basic.android.basiclauncher.R;

import java.util.Timer;
import java.util.TimerTask;

public class SearchView extends FrameLayout {

    private Context context;
    private TextSwitcher textSwitcher;
    private ConstraintLayout keyboard;
    private ConstraintLayout assistant;
    private ImageView imageViewAssistant;
    private ImageView imageViewKeyboard;
    private ImageView viewAssistant;
    private ImageView viewKeyboard;
    private float zoom;
    private String[] suggestions;
    private boolean focus = false;
    int init = 1;

    public SearchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Resources resources = getResources();
        suggestions = resources.getStringArray(R.array.search_orb_text_to_show);
        this.context = context;
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        init();
        listeners();
    }

    private void init() {
        Resources resources = getResources();
        zoom = resources.getFraction(R.fraction.app_banner_focused_scale, 1, 1);
        textSwitcher = findViewById(R.id.text_switcher);
        assistant = findViewById(R.id.micLay);
        viewAssistant = findViewById(R.id.button_background_mic);
        imageViewAssistant = findViewById(R.id.mic);
        keyboard = findViewById(R.id.keyboardLay);
        viewKeyboard = findViewById(R.id.button_background_keyboard);
        imageViewKeyboard = findViewById(R.id.keyboard);
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(context);
                Typeface typeface = ResourcesCompat.getFont(context,R.font.google_sans);
                textView.setTypeface(typeface);
                textView.setTextColor(ContextCompat.getColor(getContext(), R.color.white_50));
                textView.setTextSize(18f);
                textView.setText(suggestions[(int) Math.floor(Math.random() * ((double) suggestions.length))]);
                return textView;
            }
        });
        Animation loadAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        loadAnimation.setDuration(250);
        textSwitcher.setInAnimation(loadAnimation);
    }

    private void listeners() {
        assistant.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i != 109 && i != 23 && i != 66) {
                    return false;
                }
                try {
                    context.startActivity(new Intent("android.intent.action.ASSIST"));
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
        startTextSwitcher();

        keyboard.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b) {
                    keyboard.setVisibility(VISIBLE);
                }else {
                    keyboard.setVisibility(GONE);
                }
            }
        });
        assistant.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                ViewPropertyAnimator viewPropertyAnimator;
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_mic_color);
                Drawable drawable2 = ContextCompat.getDrawable(getContext(), R.drawable.ic_google_assistant);
                Drawable drawable3 = ContextCompat.getDrawable(getContext(), R.drawable.full_circle_background);
                float f;
                if (!b) {
                    imageViewAssistant.setVisibility(View.VISIBLE);
                    viewAssistant.setVisibility(View.GONE);
                    imageViewAssistant.setImageDrawable(drawable2);
                    f = 1.0f;
                    viewPropertyAnimator = viewAssistant.animate().z(0.0f).scaleX(1.0f);
                    String str = suggestions[(int) Math.floor(Math.random() * ((double) suggestions.length))];
                    textSwitcher.animate();
                    textSwitcher.removeAllViews();
                    textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                        @Override
                        public View makeView() {
                            TextView textView = new TextView(context);
                            Typeface typeface = ResourcesCompat.getFont(context,R.font.google_sans);
                            textView.setTypeface(typeface, Typeface.ITALIC);
                            textView.setTypeface(typeface);
                            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.white_30));
                            textView.setTextSize(18f);
                            textView.setText(suggestions[(int) Math.floor(Math.random() * ((double) suggestions.length))]);
                            return textView;
                        }
                    });
                    textSwitcher.setText(str);
                    if(!keyboard.hasFocus()) {
                        keyboard.setVisibility(GONE);
                    }
                    focus = false;
                } else {
                    viewAssistant.setVisibility(View.VISIBLE);
                    imageViewAssistant.setImageDrawable(drawable);
                    viewAssistant.setImageDrawable(drawable3);
                    viewPropertyAnimator = viewAssistant.animate().z(-2.0f).scaleX(zoom);
                    f = zoom;
                    textSwitcher.removeAllViews();
                    textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                        @Override
                        public View makeView() {
                            TextView textView = new TextView(context);
                            Typeface typeface = ResourcesCompat.getFont(context,R.font.google_sans);
                            textView.setTypeface(typeface);
                            textView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 0));
                            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.white_90));
                            textView.setTextSize(18f);
                            textView.setText(suggestions[(int) Math.floor(Math.random() * ((double) suggestions.length))]);
                            return textView;
                        }
                    });
                    textSwitcher.setText("Click to speak");
                    keyboard.setVisibility(VISIBLE);
                    focus = true;
                }
                viewPropertyAnimator.scaleY(f).setDuration(150);
            }
        });

        keyboard.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                ViewPropertyAnimator viewPropertyAnimator;
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_keyboard_gray);
                Drawable drawable2 = ContextCompat.getDrawable(getContext(), R.drawable.ic_keyboard_black);
                Drawable drawable3 = ContextCompat.getDrawable(getContext(), R.drawable.full_circle_background);
                float f;
                if (!b) {
                    viewKeyboard.setVisibility(View.GONE);
                    imageViewKeyboard.setImageDrawable(drawable);
                    f = 1.0f;
                    viewPropertyAnimator = viewKeyboard.animate().z(0.0f).scaleX(1.0f);
                    String str = suggestions[(int) Math.floor(Math.random() * ((double) suggestions.length))];
                    textSwitcher.animate();
                    textSwitcher.removeAllViews();
                    textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                        @Override
                        public View makeView() {
                            TextView textView = new TextView(context);
                            Typeface typeface = ResourcesCompat.getFont(context,R.font.google_sans);
                            textView.setTypeface(typeface, Typeface.ITALIC);
                            textView.setTypeface(typeface);
                            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.white_30));
                            textView.setTextSize(18f);
                            textView.setText(suggestions[(int) Math.floor(Math.random() * ((double) suggestions.length))]);
                            return textView;
                        }
                    });
                    textSwitcher.setText(str);
                    if(!assistant.hasFocus()) {
                        keyboard.setVisibility(GONE);
                    }
                    focus = false;
                } else {
                    imageViewKeyboard.setImageDrawable(drawable2);
                    viewKeyboard.setVisibility(View.VISIBLE);
                    viewKeyboard.setImageDrawable(drawable3);
                    viewPropertyAnimator = viewKeyboard.animate().z(-2.0f).scaleX(zoom);
                    f = zoom;
                    textSwitcher.removeAllViews();
                    textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                        @Override
                        public View makeView() {
                            TextView textView = new TextView(context);
                            Typeface typeface = ResourcesCompat.getFont(context,R.font.google_sans);
                            textView.setTypeface(typeface);
                            textView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 0));
                            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.white_90));
                            textView.setTextSize(18f);
                            textView.setText(suggestions[(int) Math.floor(Math.random() * ((double) suggestions.length))]);
                            return textView;
                        }
                    });
                    textSwitcher.setText("Click to type");
                    focus = true;
                }
                viewPropertyAnimator.scaleY(f).setDuration(150);
            }
        });

        keyboard.setOnClickListener(new OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                Log.i("SearchView", "clicked");
                Toast.makeText(context, "Feature Coming soon!", Toast.LENGTH_SHORT).show();
//                Intent launchIntent = null;
//                try{
//                    launchIntent = getContext().getPackageManager().getLaunchIntentForPackage("com.google.android.katniss").setClassName("com.google.android.katniss", ".search.KeyboardSearchActivity");
//                } catch (Exception ignored) {
//                    ignored.printStackTrace();
//                }
//
//                if(launchIntent == null){
//                    getContext().startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://play.google.com/store/apps/details?id=" + "com.google.android.katniss")));
//                } else {
//                    getContext().startActivity(launchIntent);
//                }
//                getContext().startActivity(getContext().getPackageManager().getLaunchIntentForPackage("com.google.android.katniss"));
                String url = "https://www.google.com.ua";
            }
        });
    }

    private void startTextSwitcher() {
        Timer timer = new Timer();
        TimerTask doAsynchronousTask;
        if( init == 1) {
            textSwitcher.removeAllViews();
            textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                @Override
                public View makeView() {
                    TextView textView = new TextView(context);
                    Typeface typeface = ResourcesCompat.getFont(context,R.font.google_sans);
                    textView.setTypeface(typeface, Typeface.ITALIC);
                    textView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 0));
                    textView.setTextColor(ContextCompat.getColor(getContext(), R.color.white_30));
                    textView.setTextSize(18f);
                    textView.setText(suggestions[(int) Math.floor(Math.random() * ((double) suggestions.length))]);
                    return textView;
                }
            });
            textSwitcher.setText("Search movies, TV, and more");
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    init++;
                    startTextSwitcher();
                }
            }, 20000);
        } else {
            doAsynchronousTask = new TimerTask() {
                @Override
                public void run() {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                if (!focus) {
                                    textSwitcher.removeAllViews();
                                    textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                                        @Override
                                        public View makeView() {
                                            TextView textView = new TextView(context);
                                            Typeface typeface = ResourcesCompat.getFont(context,R.font.google_sans);
                                            textView.setTypeface(typeface, Typeface.ITALIC);
                                            textView.setTypeface(typeface);
                                            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.white_30));
                                            textView.setTextSize(18f);
                                            textView.setText(suggestions[(int) Math.floor(Math.random() * ((double) suggestions.length))]);
                                            return textView;
                                        }
                                    });
                                    String str = suggestions[(int) Math.floor(Math.random() * ((double) suggestions.length))];
                                    textSwitcher.animate();
                                    textSwitcher.setText(str);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            };
            timer.schedule(doAsynchronousTask, 0, 10000);
        }
    }

}
