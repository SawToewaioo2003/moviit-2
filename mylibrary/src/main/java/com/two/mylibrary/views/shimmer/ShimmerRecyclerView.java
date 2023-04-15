package com.two.mylibrary.views.shimmer;


import static androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.CallSuper;
import androidx.annotation.IntDef;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.two.mylibrary.R;
import com.two.mylibrary.views.shimmer.Shimmer.Direction;
import com.two.mylibrary.views.shimmer.Shimmer.Shape;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP;

public class ShimmerRecyclerView extends RecyclerView {

    public enum LayoutMangerType {
        LINEAR_VERTICAL, LINEAR_HORIZONTAL, GRID
    }

    private ShimmerAdapter mShimmerAdapter;
    private LayoutManager mShimmerLayoutManager;

    private LayoutManager mActualLayoutManager;
    private Adapter mActualAdapter;


    private int mLayoutReference = R.layout.shimmer_simple_view;
    private boolean mCanScroll;
    private LayoutMangerType mLayoutMangerType = LayoutMangerType.GRID;
    private int mGridCount = 3;

    public ShimmerRecyclerView(Context context) {
        super(context);
        init(null);
    }

    public ShimmerRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ShimmerRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        mShimmerAdapter = new ShimmerAdapter();
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ShimmerRecyclerView, 0, 0);
        try {
            if (a.hasValue(R.styleable.ShimmerRecyclerView_demo_layout)) {
                setDemoLayoutReference(a.getResourceId(R.styleable.ShimmerRecyclerView_demo_layout, R.layout.shimmer_simple_view));
            }

            if (a.hasValue(R.styleable.ShimmerRecyclerView_demo_child_count)) {
                setDemoChildCount(a.getInteger(R.styleable.ShimmerRecyclerView_demo_child_count, 1));
            }

            if (a.hasValue(R.styleable.ShimmerRecyclerView_demo_layout_manager_type)) {
                int value = a.getInteger(R.styleable.ShimmerRecyclerView_demo_layout_manager_type, 0);
                switch (value) {
                    case 1:
                        setDemoLayoutManager(LayoutMangerType.LINEAR_HORIZONTAL);
                        break;
                    case 2:
                        setDemoLayoutManager(LayoutMangerType.GRID);
                        break;
                    case 0:
                    default:
                        setDemoLayoutManager(LayoutMangerType.LINEAR_VERTICAL);
                        break;

                }
            }

            if (a.hasValue(R.styleable.ShimmerRecyclerView_demo_grid_child_count)) {
                setGridChildCount(a.getInteger(R.styleable.ShimmerRecyclerView_demo_grid_child_count, 2));
            }

        } finally {
            a.recycle();
        }

        showShimmerAdapter();


    }

    /**
     * Specifies the number of child should exist in any row of the grid layout.
     *
     * @param count - count specifying the number of child.
     */
    public void setGridChildCount(int count) {
        mGridCount = count;
    }

    /**
     * Sets the layout manager for the shimmer adapter.
     *
     * @param type layout manager reference
     */
    public void setDemoLayoutManager(LayoutMangerType type) {
        mLayoutMangerType = type;

    }

    /**
     * Sets the number of demo views should be shown in the shimmer adapter.
     *
     * @param count - number of demo views should be shown.
     */
    public void setDemoChildCount(int count) {
        mShimmerAdapter.setMinItemCount(count);
    }

    /**
     * Sets the shimmer adapter and shows the loading screen.
     */
    public void showShimmerAdapter() {
        mCanScroll = true;

        if (mShimmerLayoutManager == null) {
            initShimmerManager();
        }

        setLayoutManager(mShimmerLayoutManager);
        setAdapter(mShimmerAdapter);

    }

    private void initShimmerManager() {

        switch (mLayoutMangerType) {
            case LINEAR_VERTICAL:
                mShimmerLayoutManager = new LinearLayoutManager(getContext()) {
                    public boolean canScrollVertically() {
                        return mCanScroll;
                    }
                };
                break;
            case LINEAR_HORIZONTAL:
                mShimmerLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false) {
                    public boolean canScrollHorizontally() {
                        return mCanScroll;
                    }
                };
                break;
            case GRID:
                mShimmerLayoutManager = new GridLayoutManager(getContext(), mGridCount) {
                    public boolean canScrollVertically() {
                        return mCanScroll;
                    }
                };
                break;


        }
    }

    /**
     * Hides the shimmer adapter
     */
    public void hideShimmerAdapter() {
        mCanScroll = true;
        setLayoutManager(mActualLayoutManager);
        setAdapter(mActualAdapter);
    }

    public void setLayoutManager(LayoutManager manager,int count){
        mShimmerLayoutManager= new GridLayoutManager(getContext(),count);
        mGridCount=20;
        mShimmerAdapter.setMinItemCount(20);
        if (manager == null) {
            mActualLayoutManager = null;
        } else if (manager != mShimmerLayoutManager) {
            mActualLayoutManager = manager;
            super.setLayoutManager(mActualLayoutManager);
        }else {

            super.setLayoutManager(manager);
        }


    }
    public void setLayoutManager(LayoutManager manager) {

        if (manager == null) {
            mActualLayoutManager = null;
        } else if (manager != mShimmerLayoutManager) {
            mActualLayoutManager = manager;
            super.setLayoutManager(mActualLayoutManager);
        }else {

        super.setLayoutManager(manager);
        }

    }


    public void setAdapter(Adapter adapter) {

        if (adapter == null) {
            mActualAdapter = null;
        } else if (adapter != mShimmerAdapter) {
            mActualAdapter = adapter;
            super.setAdapter(mActualAdapter);
        }else {

            super.setAdapter(adapter);
        }
    }


    public int getLayoutReference() {
        return mLayoutReference;
    }

    /**
     * Sets the demo layout reference
     *
     * @param mLayoutReference layout resource id of the layout which should be shown as demo.
     */
    public void setDemoLayoutReference(int mLayoutReference) {
        this.mLayoutReference = mLayoutReference;
        mShimmerAdapter.setLayoutReference(getLayoutReference());
    }
}