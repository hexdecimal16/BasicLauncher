package com.basic.android.basiclauncher;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.leanback.widget.HorizontalGridView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.basic.android.basiclauncher.view.AddActivity;
import com.basic.android.basiclauncher.view.Row;
import com.basic.android.basiclauncher.widgets.EditableRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Home extends Activity {

    public RecyclerView recyclerView;
    public ConstraintLayout linearLayout;
    public Context context;
    private ArrayList<String> arrayList = new ArrayList<>();
    private InstalledAppsAdapter adapter;
    private static NestedScrollView rootView;

    /* renamed from: a */
    public void updateRecyclers() {
        Intent intent = new Intent("android.intent.action.MAIN", null);
        intent.addCategory(Intent.CATEGORY_LEANBACK_LAUNCHER);
        List<ResolveInfo> queryIntentActivities = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_ALL);
        arrayList.clear();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            arrayList.add(resolveInfo.activityInfo.applicationInfo.packageName);
        }
        adapter.notifyDataSetChanged();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_home_new);
        init();
        permission();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        Intent intent = new Intent("android.intent.action.MAIN", null);
        intent.addCategory("android.intent.category.LEANBACK_LAUNCHER");
        List<ResolveInfo> queryIntentActivities = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_ALL);
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            arrayList.add(resolveInfo.activityInfo.applicationInfo.packageName);
        }
        adapter = new InstalledAppsAdapter(context, arrayList);
        recyclerView.setAdapter(adapter);
    }

    private void init() {
        context = this;
        recyclerView = findViewById(R.id.recyler_view_apps);
        rootView = findViewById(R.id.main_browse_fragment);
        linearLayout = findViewById(R.id.row_titleFavorite);
        arrayList = new ArrayList<>();
    }

    private void permission() {
        if (ActivityCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (linearLayout.isFocused() && (keyEvent.getKeyCode() == 109 || keyEvent.getKeyCode() == 23 || keyEvent.getKeyCode() == 66)) {
            context.startActivity(new Intent(this.context, AddActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        }
        return super.onKeyUp(i, keyEvent);
    }

    public void onResume() {
        super.onResume();
        updateRecyclers();
    }

    public void onStart() {
        super.onStart();
        updateRecyclers();
        Row.setFavorite("");
    }

    public static void changeColor(int color) {
        final GradientDrawable drawable = new GradientDrawable();
        String hexColor = String.format("#%06X", (0xFFFFFF & color));
        drawable.setShape(GradientDrawable.RECTANGLE);
        final String c1 = hexColor.substring(0, 1) + "00" + hexColor.substring(1);
        final String c15 = hexColor.substring(0, 1) + "26" + hexColor.substring(1);
        drawable.setOrientation(GradientDrawable.Orientation.BL_TR);
        ValueAnimator alphaAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), Color.parseColor(c1), Color.parseColor(c15));
        alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int[] colors = { Color.parseColor(c1), (int) valueAnimator.getAnimatedValue()};
                drawable.setColors(colors);
                rootView.setBackground(drawable);
            }
        });
        alphaAnimator.setDuration(1000);
        alphaAnimator.start();
    }

    public void arrange() {
        EditableRecyclerVie
    }
}
