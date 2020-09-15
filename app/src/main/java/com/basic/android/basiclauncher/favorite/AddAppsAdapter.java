package com.basic.android.basiclauncher.favorite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import com.basic.android.basiclauncher.AppsManager;
import com.basic.android.basiclauncher.R;
import com.basic.android.basiclauncher.Home;
import com.basic.android.basiclauncher.view.Row;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

import java.util.ArrayList;

public class AddAppsAdapter extends RecyclerView.Adapter<AddAppsAdapter.AppsViewHolder> {
    public static final String PACKAGE = "package";
    /* access modifiers changed from: private */
    public Context mContext;
    private ArrayList<String> mDataSet;

    public AddAppsAdapter(Context context, ArrayList<String> arrayList) {
        this.mContext = context;
        this.mDataSet = arrayList;
    }

    public AppsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new AppsViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.icon, viewGroup, false));
    }

    public void onBindViewHolder(AppsViewHolder appsViewHolder, int i) {
        AppsManager appsManager = new AppsManager(this.mContext);
        final String str = this.mDataSet.get(i);
        String applicationLabelByPackageName = appsManager.getApplicationLabelByPackageName(str);
        Drawable appIconByPackageName = appsManager.getAppIconByPackageName(str);
        Palette.Swatch vibrantSwatch = Palette.from(getBitmapFromDrawable(appIconByPackageName)).generate().getVibrantSwatch();
        if (vibrantSwatch != null) {
            vibrantSwatch.getRgb();
        }
        ((RequestBuilder) Glide.with(this.mContext).load(appIconByPackageName).fitCenter()).into(appsViewHolder.mImageViewIcon);
        appsViewHolder.mImageViewIcon.setContentDescription(applicationLabelByPackageName);
        appsViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            public void onClick(View view) {
                Row.setFavorite(str);
                Intent intent = new Intent(AddAppsAdapter.this.mContext, Home.class);
                intent.setFlags(603979776);
                intent.putExtra(AddAppsAdapter.PACKAGE, str);
                AddAppsAdapter.this.mContext.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return this.mDataSet.size();
    }

    class AppsViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout mCardView;
        ImageView mImageViewIcon;
        TextView mTextViewLabel;
        RecyclerView recyclerView;

        AppsViewHolder(View view) {
            super(view);
            this.mCardView = view.findViewById(R.id.card_view);
            this.mTextViewLabel = view.findViewById(R.id.text_view_icon_title);
            this.mImageViewIcon = view.findViewById(R.id.image_view);
            this.recyclerView = view.findViewById(R.id.recycler_view_add);
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