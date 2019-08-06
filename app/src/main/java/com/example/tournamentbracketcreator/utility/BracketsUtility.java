package com.example.tournamentbracketcreator.utility;

import android.util.DisplayMetrics;
import com.example.tournamentbracketcreator.application.BracketsApplication;

public class BracketsUtility {
    public static int dpToPx(int dp){
//        DisplayMetrics displayMetrics = BracketsApplication.getInstance()
//                .getBaseContext().getResources().getDisplayMetrics();

        DisplayMetrics displayMetrics = BracketsApplication.getInstance()
                .getApplicationContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
