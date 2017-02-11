package com.brianneldon.ilovezappos;

import android.content.Context;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Brian on 2/9/2017.
 * Custom viewpager for displaying ProductFragments
 */

public class ProductViewPager extends ViewPager {
    FragmentStatePagerAdapter pagerAdapter;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (pagerAdapter != null) {
            super.setAdapter(pagerAdapter);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int height = 0;
        for(int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if(h > height) height = h;
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void storeAdapter(FragmentStatePagerAdapter pagerAdapter) {
        this.pagerAdapter = pagerAdapter;
    }

    public ProductViewPager(Context context) {
        super(context);
    }

    public ProductViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
