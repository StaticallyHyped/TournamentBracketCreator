package com.example.tournamentbracketcreator.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class HSlideAnimation extends Animation {

    int mFromHeight;
    int mToHeight;
    View mView;

    public HSlideAnimation(View view, int fromHeight, int toHeight) {
        this.mFromHeight = fromHeight;
        this.mToHeight = toHeight;
        this.mView = view;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newHeight;

        if (mView.getHeight() != mToHeight){
            newHeight = (int) ((mFromHeight + (mToHeight - mFromHeight) * interpolatedTime));
            mView.getLayoutParams().height = newHeight;
            mView.requestLayout();
        }
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}
