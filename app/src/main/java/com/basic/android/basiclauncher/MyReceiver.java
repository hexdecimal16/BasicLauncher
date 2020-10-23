package com.basic.android.basiclauncher;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Objects;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("MyReceiver", "Got new package");
        switch (Objects.requireNonNull(intent.getAction())) {
            case Intent.ACTION_PACKAGE_INSTALL:
                //what you want to do
                break;
            case Intent.ACTION_BOOT_COMPLETED:
                //what you want to do
                break;
        }
    }
}
