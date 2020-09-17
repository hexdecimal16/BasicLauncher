package com.basic.android.basiclauncher.view;

import android.content.Context;
import android.os.Environment;
import android.util.AttributeSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.basic.android.basiclauncher.CenterLayoutManager;
import com.basic.android.basiclauncher.InstalledAppsAdapter;
import com.basic.android.basiclauncher.R;
import com.basic.android.basiclauncher.favorite.AddAppsAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Row extends RecyclerView {

    public static Context context;
    public static InstalledAppsAdapter addAppsAdapter;
    public static ArrayList<String> list = new ArrayList<>();
    public static String packages = "packages";
    public static RecyclerView recyclerView;

    public Row(Context cont, AttributeSet attributeSet) {
        super(cont, attributeSet);
        context = cont;
    }

    public static void setFavorite(String str) {
        File file = new File(Environment.getExternalStorageDirectory().toString(), "BasicLauncher");
        File file2 = new File(file, packages);
        if (!file2.canRead()) {
            file.mkdir();
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!str.equalsIgnoreCase("")) {
            try {
                FileWriter fileWriter = new FileWriter(file2, true);
                fileWriter.append(str + "\n");
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        try {
            Scanner scanner = new Scanner(file2);
            list = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (nextLine != null) {
                    list.add(nextLine);
                }
            }
        } catch (FileNotFoundException e3) {
            e3.printStackTrace();
        }
        InstalledAppsAdapter bVar = new InstalledAppsAdapter(context, list);
        addAppsAdapter = bVar;
        recyclerView.setAdapter(bVar);
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        RecyclerView recyclerView = findViewById(R.id.recycler_view_add);
        Row.recyclerView = recyclerView;
        recyclerView.setLayoutManager(new CenterLayoutManager(context, LinearLayoutManager.HORIZONTAL , false));
    }

    protected boolean isPaddingOffsetRequired() {
        return true;
    }

    @Override
    protected int getLeftPaddingOffset() {
        return -getPaddingTop();
    }

    @Override
    protected int getRightPaddingOffset() {
        return getPaddingBottom();
    }
}
