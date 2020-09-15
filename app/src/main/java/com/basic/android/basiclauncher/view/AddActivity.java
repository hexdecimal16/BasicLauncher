package com.basic.android.basiclauncher.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.basic.android.basiclauncher.Home;
import com.basic.android.basiclauncher.R;
import com.basic.android.basiclauncher.favorite.AddAppsAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AddActivity extends Activity {

    /* renamed from: a */
    public LinearZoom f3938a;

    /* renamed from: b */
    public AddAppsAdapter f3939b;

    /* renamed from: c */
    public Context f3940c;

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @SuppressLint("WrongConstant")
    public final void onAttachedToWindow() {
        RecyclerView recyclerView = findViewById(R.id.row_list_view);
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
        ArrayList arrayList2 = new ArrayList();
        File file = new File(Environment.getExternalStorageDirectory().toString(), "BasicLauncher");
        File file2 = new File(file, "packages");
        if (!file2.canRead()) {
            file.mkdir();
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Scanner scanner = new Scanner(file2);
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (nextLine != null) {
                    arrayList2.add(nextLine);
                }
            }
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        ArrayList arrayList3 = new ArrayList(arrayList);
        arrayList3.addAll(arrayList2);
        ArrayList arrayList4 = new ArrayList(arrayList);
        arrayList4.retainAll(arrayList2);
        arrayList3.removeAll(arrayList4);
        ArrayList arrayList5 = new ArrayList();
        Iterator it = arrayList3.iterator();
        while (it.hasNext()) {
            arrayList5.add(it.next());
        }
        this.f3939b = new AddAppsAdapter(this, arrayList5);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(this.f3939b);
        this.f3938a = findViewById(R.id.linearLayout);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.favorite_app_preference);
        this.f3940c = this;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.f3938a.isFocused() && (i == 109 || i == 23 || i == 66)) {
            new File(new File(Environment.getExternalStorageDirectory().toString(), "BasicLauncher"), "packages").delete();
            this.f3940c.startActivity(new Intent(this.f3940c, Home.class));
        }
        return super.onKeyUp(i, keyEvent);
    }
}