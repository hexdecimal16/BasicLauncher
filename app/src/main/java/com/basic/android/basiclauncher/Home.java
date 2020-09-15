package com.basic.android.basiclauncher;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.basic.android.basiclauncher.view.AddActivity;
import com.basic.android.basiclauncher.view.Row;
import java.util.ArrayList;
import java.util.List;

public class Home extends Activity {

    /* renamed from: a */
    public InstalledAppsAdapter f3909a;

    /* renamed from: b */
    public RecyclerView f3910b;

    /* renamed from: c */
    public LinearLayout f3911c;

    /* renamed from: d */
    public Context f3912d;

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
        this.f3909a = bVar;
        this.f3910b.setAdapter(bVar);
        RecyclerView recyclerView = findViewById(R.id.recyler_view_apps);
        this.f3910b = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        this.f3910b.setAdapter(this.f3909a);
    }

    @SuppressLint("WrongConstant")
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f3912d = this;
        setContentView(R.layout.activity_home);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyler_view_apps);
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
        this.f3909a = bVar;
        this.f3910b.setAdapter(bVar);
        if (ActivityCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        } else {

        }
        this.f3911c = findViewById(R.id.favorite_contain);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.f3911c.isFocused() && (keyEvent.getKeyCode() == 109 || keyEvent.getKeyCode() == 23 || keyEvent.getKeyCode() == 66)) {
            this.f3912d.startActivity(new Intent(this.f3912d, AddActivity.class));
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
}
