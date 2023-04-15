package com.two.mylibrary.views.shimmer;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.two.mylibrary.R;

public class ShimmerViewHolder extends RecyclerView.ViewHolder {

    public ShimmerViewHolder(LayoutInflater inflater, ViewGroup parent, int innerViewResId) {
        super(inflater.inflate(R.layout.recyclerview_shimmer_viewholder_layout, parent, false));
        ShimmerFrameLayout layout = (ShimmerFrameLayout) itemView;

        View innerView = inflater.inflate(innerViewResId, layout, false);
        layout.addView(innerView);
      //  layout.setAutoStart(false);
    }

    /**
     * Binds the view
     */
    public void bind() {

        ShimmerFrameLayout layout = (ShimmerFrameLayout) itemView;
        layout.startShimmer();
    }
}