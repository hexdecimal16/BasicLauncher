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

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InstalledAppsAdapter extends RecyclerView.Adapter<InstalledAppsAdapter.AppsViewHolder> {

    public Context mContext;
    private List<String> mDataSet;
    private int dragDirs;
    private int swipeDirs;

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

    public void onBindViewHolder(@NotNull final AppsViewHolder appsViewHolder, int i) {
        final AppsManager appsManager = new AppsManager(this.mContext);
        final String str = this.mDataSet.get(i);
        if (str != null) {
            String applicationLabelByPackageName = appsManager.getApplicationLabelByPackageName(str);
            Drawable appIconByPackageName = appsManager.getAppIconByPackageName(str);
            if (getBitmapFromDrawable(appIconByPackageName).getPixel(0, 0) == Color.TRANSPARENT) {
                appsViewHolder.mImageViewIcon.setBackgroundColor(ContextCompat.getColor(mContext, R.color.iconBackGray));
            }
            Palette.Swatch vibrantSwatch = Palette.from(getBitmapFromDrawable(appIconByPackageName)).generate().getVibrantSwatch();
            if (vibrantSwatch != null) {
                vibrantSwatch.getRgb();
            }
            appsViewHolder.mTextViewLabel.setText(applicationLabelByPackageName);
            Glide.with(this.mContext)
                    .load(appIconByPackageName)
                    .fitCenter()
                    .transform(new RoundedCorners(10))
                    .into(appsViewHolder.mImageViewIcon);
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
            appsViewHolder.mCardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Log.d("InstalledAppsAdapter", "onLongClick");
                    return true;
                }
            });
            appsViewHolder.mCardView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if( b) {
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

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };


    public void moveChannel(int from, int to) {
        int offset = 1;
        if (from >= 0 && from <= mDataSet.size() - 1 && to >= 0 && to <= mDataSet.size() - 1) {
            String fromItem = mDataSet.get(from);
            mDataSet.add(from, mDataSet.get(to));
            mDataSet.add(to, fromItem);
            notifyItemMoved(from, to);

            int positionDifference = to - from;
            if (Math.abs(positionDifference) > 1) {
                if (positionDifference > 0) {
                    offset = -1;
                }
                notifyItemMoved(to + offset, from);
            }
        }
    }
}