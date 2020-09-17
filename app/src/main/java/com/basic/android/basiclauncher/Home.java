package com.basic.android.basiclauncher;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.basic.android.basiclauncher.view.AddActivity;
import com.basic.android.basiclauncher.view.Row;
import java.util.ArrayList;
import java.util.List;

public class Home extends Activity {
    
    public InstalledAppsAdapter installedAppsAdapter;
    public RecyclerView f3910b;
    public ConstraintLayout linearLayout;
    public Context context;
    private static NestedScrollView rootView;

    /* renamed from: a */
    @SuppressLint("WrongConstant")
    public void updateRecyclers() {
        Intent intent = new Intent("android.intent.action.MAIN", null);
        Intent intent2 = new Intent("android.intent.action.MAIN", null);
        intent.addCategory("android.intent.category.LEANBACK_LAUNCHER");
        intent2.addCategory("android.intent.category.LAUNCHER");
        intent.setFlags(270532608);
        List<ResolveInfo> queryIntentActivities = getPackageManager().queryIntentActivities(intent, 131072);
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            arrayList.add(resolveInfo.activityInfo.applicationInfo.packageName);
        }
        InstalledAppsAdapter bVar = new InstalledAppsAdapter(this, arrayList);
        this.installedAppsAdapter = bVar;
        this.f3910b.setAdapter(bVar);
        RecyclerView recyclerView = findViewById(R.id.recyler_view_apps);
        this.f3910b = recyclerView;
        recyclerView.setLayoutManager(new CenterLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        this.f3910b.setAdapter(this.installedAppsAdapter);

    }

    @SuppressLint("WrongConstant")
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.context = this;
        setContentView(R.layout.activity_home_new);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyler_view_apps);
        rootView = findViewById(R.id.main_browse_fragment);
        this.f3910b = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        Intent intent2 = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LEANBACK_LAUNCHER");
        intent2.addCategory("android.intent.category.LAUNCHER");
        intent.setFlags(270532608);
        List<ResolveInfo> queryIntentActivities = getPackageManager().queryIntentActivities(intent, 131072);
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            arrayList.add(resolveInfo.activityInfo.applicationInfo.packageName);
        }
        InstalledAppsAdapter bVar = new InstalledAppsAdapter(this, arrayList);
        this.installedAppsAdapter = bVar;
        this.f3910b.setAdapter(bVar);
        if (ActivityCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        } else {

        }
        this.linearLayout = findViewById(R.id.row_titleFavorite);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.linearLayout.isFocused() && (keyEvent.getKeyCode() == 109 || keyEvent.getKeyCode() == 23 || keyEvent.getKeyCode() == 66)) {
            this.context.startActivity(new Intent(this.context, AddActivity.class));
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
        GradientDrawable drawable = new GradientDrawable();
        String hexColor = String.format("#%06X", (0xFFFFFF & color));
        String c1 = hexColor.substring(0, 1) + "00" + hexColor.substring(1);
        String c10 = hexColor.substring(0, 1) + "1A" + hexColor.substring(1);
        String c30 = hexColor.substring(0, 1) + "4D" + hexColor.substring(1);
        String c40 = hexColor.substring(0, 1) + "66" + hexColor.substring(1);
        String c50 = hexColor.substring(0, 1) + "80" + hexColor.substring(1);
        String c60 = hexColor.substring(0, 1) + "99" + hexColor.substring(1);
        String c70 = hexColor.substring(0, 1) + "B3" + hexColor.substring(1);
        String c80 = hexColor.substring(0, 1) + "CC" + hexColor.substring(1);
        String c90 = hexColor.substring(0, 1) + "E6" + hexColor.substring(1);
        drawable.setOrientation(GradientDrawable.Orientation.BL_TR);
        int[] colors = { Color.parseColor(c1), Color.parseColor(c10)};

        Log.i("colors", colors.toString());
        drawable.setColors(colors);
        rootView.setBackground(drawable);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(drawable, PropertyValuesHolder.ofInt("alpha", 255));
        animator.setTarget(drawable);
        animator.setDuration(2000);
        animator.start();
    }
}
