package com.basic.android.basiclauncher;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.core.content.ContextCompat;
import androidx.core.content.IntentCompat;
import java.util.ArrayList;
import java.util.List;

public class AppsManager {
    private Context mContext;

    public AppsManager(Context context) {
        this.mContext = context;
    }

    public ArrayList<String> getInstalledPackages() {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        Intent intent2 = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory(IntentCompat.CATEGORY_LEANBACK_LAUNCHER);
        intent2.addCategory("android.intent.category.LAUNCHER");
        intent.setFlags(270532608);
        List<ResolveInfo> queryIntentActivities = this.mContext.getPackageManager().queryIntentActivities(intent, 131072);
        ArrayList<String> arrayList = new ArrayList<>();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            arrayList.add(resolveInfo.activityInfo.applicationInfo.packageName);
        }
        return arrayList;
    }

    public Drawable getAppIconByPackageName(String str) {
        try {
            Drawable applicationBanner = this.mContext.getPackageManager().getApplicationBanner(str);
            if (applicationBanner != null) {
                return applicationBanner;
            }
            Drawable applicationIcon = this.mContext.getPackageManager().getApplicationIcon(str);
            if (applicationIcon == null) {
                return ContextCompat.getDrawable(this.mContext, R.drawable.lb_ic_stop);
            }
            return applicationIcon;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return ContextCompat.getDrawable(this.mContext, R.drawable.lb_ic_stop);
        }
    }

    public String getApplicationLabelByPackageName(String str) {
        PackageManager packageManager = this.mContext.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                return (String) packageManager.getApplicationLabel(applicationInfo);
            }
            return "Unknown";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "Unknown";
        }
    }
}