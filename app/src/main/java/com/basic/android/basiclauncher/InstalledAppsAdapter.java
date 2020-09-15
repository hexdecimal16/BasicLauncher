package com.basic.android.basiclauncher;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InstalledAppsAdapter extends RecyclerView.Adapter<InstalledAppsAdapter.AppsViewHolder> {

    public Context mContext;
    private List<String> mDataSet;

    public InstalledAppsAdapter(Context context, ArrayList<String> arrayList) {
        arrayList.removeAll(Collections.singleton((Object) null));
        this.mContext = context;
        this.mDataSet = arrayList;
        Log.d("List", arrayList.toString());
    }

    @NotNull
    public AppsViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        return new AppsViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.icon, viewGroup, false));
    }

    public void onBindViewHolder(@NotNull AppsViewHolder appsViewHolder, int i) {
        final AppsManager appsManager = new AppsManager(this.mContext);
        final String str = this.mDataSet.get(i);
        if (str != null) {
            String applicationLabelByPackageName = appsManager.getApplicationLabelByPackageName(str);
            Drawable appIconByPackageName = appsManager.getAppIconByPackageName(str);
            Palette.Swatch vibrantSwatch = Palette.from(getBitmapFromDrawable(appIconByPackageName)).generate().getVibrantSwatch();
            if (vibrantSwatch != null) {
                vibrantSwatch.getRgb();
            }
            Log.d("this is my color", String.valueOf(-16776961));
            appsViewHolder.mTextViewLabel.setText(applicationLabelByPackageName);
            ((RequestBuilder) Glide.with(this.mContext).load(appIconByPackageName).fitCenter()).into(appsViewHolder.mImageViewIcon);
            appsViewHolder.mImageViewIcon.setContentDescription(applicationLabelByPackageName);
            appsViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("WrongConstant")
                public void onClick(View view) {
                    Intent leanbackLaunchIntentForPackage = InstalledAppsAdapter.this.mContext.getPackageManager().getLeanbackLaunchIntentForPackage(str);
                    if (leanbackLaunchIntentForPackage != null) {
                        InstalledAppsAdapter.this.mContext.startActivity(leanbackLaunchIntentForPackage);
                        return;
                    }
                    Context access$000 = InstalledAppsAdapter.this.mContext;
                    Toast.makeText(access$000, str + " Launch Error.", 0).show();
                }
            });
            appsViewHolder.mCardView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if( b) {
                        Log.i("onFocusChange", "focused: " + b);
                        Palette.from(getBitmapFromDrawable(appsManager.getAppIconByPackageName(str))).generate(new Palette.PaletteAsyncListener() {
                            public void onGenerated(Palette p) {
                                int color = p.getDominantColor(Color.BLACK);
                                Home.changeColor(color);
                            }
                        });
                    }
                }
            });
        }
    }

    public int getItemCount() {
        return this.mDataSet.size();
    }

    static class AppsViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout mCardView;
        ImageView mImageViewIcon;
        TextView mTextViewLabel;

        AppsViewHolder(View view) {
            super(view);
            this.mCardView = view.findViewById(R.id.card_view);
            this.mTextViewLabel = view.findViewById(R.id.text_view_icon_title);
            this.mImageViewIcon = view.findViewById(R.id.image_view);
        }
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }
}