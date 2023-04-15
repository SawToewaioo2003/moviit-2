package com.two.mylibrary.views.baseClasses.globalEnums;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.two.mylibrary.views.shimmer.Shimmer;
import com.two.mylibrary.views.shimmer.ShimmerFrameLayout;


public class ShimmerViewHolder extends RecyclerView.ViewHolder {

    public final ShimmerFrameLayout mShimmer;

    public ShimmerViewHolder(@NonNull ShimmerFrameLayout itemView) {
        super(itemView);
        this.mShimmer = itemView;
    }

    /**
     * Updates shimmer properties
     */
    public void bindView(Shimmer shimmer) {
        mShimmer.setShimmer(shimmer).startShimmer();
    }
}
