package com.basic.android.basiclauncher.view;

import android.content.Context;
import android.os.Environment;
import android.util.AttributeSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.basic.android.basiclauncher.R;
import com.basic.android.basiclauncher.favorite.AddAppsAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Row extends RecyclerView {

    /* renamed from: E0 */
    public static Context context;

    /* renamed from: F0 */
    public static AddAppsAdapter f3943F0;

    /* renamed from: G0 */
    public static ArrayList<String> f3944G0 = new ArrayList<>();

    /* renamed from: H0 */
    public static String f3945H0 = "packages";

    /* renamed from: I0 */
    public static RecyclerView f3946I0;

    public Row(Context cont, AttributeSet attributeSet) {
        super(cont, attributeSet);
        context = cont;
    }

    public static void setFavorite(String str) {
        File file = new File(Environment.getExternalStorageDirectory().toString(), "BasicLauncher");
        File file2 = new File(file, f3945H0);
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
            f3944G0 = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (nextLine != null) {
                    f3944G0.add(nextLine);
                }
            }
        } catch (FileNotFoundException e3) {
            e3.printStackTrace();
        }
        AddAppsAdapter bVar = new AddAppsAdapter(context, f3944G0);
        f3943F0 = bVar;
        f3946I0.setAdapter(bVar);
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        RecyclerView recyclerView = findViewById(R.id.recycler_view_add);
        f3946I0 = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL , false));
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
