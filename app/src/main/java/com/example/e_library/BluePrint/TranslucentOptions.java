package com.example.e_library.BluePrint;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.e_library.R;

public class TranslucentOptions {

    public void DisableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    public void onlyPortraitScreen(Activity act){
        act.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void onlyTransparentStatusBar(Window newWindow, Activity act){
        onlyPortraitScreen(act);
        DisableDarkMode();

        newWindow.setFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        newWindow.setStatusBarColor(Color.WHITE);
    }

    public void withBGStatusBar(Window newWindow, Activity act){
        onlyPortraitScreen(act);
        DisableDarkMode();

        newWindow.setFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        View decorView = newWindow.getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        newWindow.setStatusBarColor(act.getResources().getColor(R.color.primary_color));
    }

    public void TransparentStatusAndNavigation(Window newWindow, Activity act){
        onlyPortraitScreen(act);
        DisableDarkMode();

        newWindow.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

}
