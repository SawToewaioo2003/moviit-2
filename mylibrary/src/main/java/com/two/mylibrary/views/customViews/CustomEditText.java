package com.two.mylibrary.views.customViews;

import android.content.Context;
import androidx.appcompat.widget.AppCompatEditText;
import android.util.AttributeSet;

public class CustomEditText extends AppCompatEditText {

    Context context;

    public CustomEditText(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void init() {
        this.setText("hello");
        this.setTextSize(24);
    }
}
