package com.example.tournamentbracketcreator.view;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

//import com.example.tournamentbracketcreator.application.BracketsApplication;

/**
 * Created by Emil on 21/10/17.
 */

public class WrapContentHtViewPager extends ViewPager {

    private Context context;

    public WrapContentHtViewPager(Context context) {
        super(context);
        this.context = context;
    }

    public WrapContentHtViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);

            child.measure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

            int h = child.getMeasuredHeight();

            if (h > height) height = h;

            //TODO when setting up animation

            /*int screenHeight = BracketsApplication.getInstance().getScreenHeight();
            if (screenHeight > height)
                height = screenHeight;*/
            //overriding wrap content feature
            // int[] screenSize = getScreenSIze();
            // height = 1800;
        }


        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int[] getScreenSIze() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int h = displaymetrics.heightPixels;
        int w = displaymetrics.widthPixels;

        int[] size = {w, h};
        return size;

    }
}