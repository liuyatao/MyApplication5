package com.gzport.gzgsearch.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * Created by 刘亚涛 on 2015/8/10.
 * 带有Pinned效果的ExpandableListView
 */
public class PinnedExpandableListView extends ExpandableListView {
    public PinnedExpandableListView(Context context) {
        super(context);
    }

    public PinnedExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PinnedExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
