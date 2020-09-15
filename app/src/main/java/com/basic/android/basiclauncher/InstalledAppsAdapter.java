package com.basic.android.basiclauncher;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InstalledAppsAdapter extends RecyclerView.Adapter<InstalledAppsAdapter.AppsViewHolder> {
    /* access modifiers changed from: private */
    public Context mContext;
    private List<String> mDataSet;

    public InstalledAppsAdapter(Context context, ArrayList<String> arrayList) {
        arrayList.removeAll(Collections.singleton((Object) null));
        this.mContext = context;
        this.mDataSet = arrayList;
        Log.d("List", arrayList.toString());
    }

    public AppsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new AppsViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.icon, viewGroup, false));
    }

    public void onBindViewHolder(AppsViewHolder appsViewHolder, int i) {
        AppsManager appsManager = new AppsManager(this.mContext);
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
        }
    }

    public int getItemCount() {
        return this.mDataSet.size();
    }

    class AppsViewHolder extends RecyclerView.ViewHolder {
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