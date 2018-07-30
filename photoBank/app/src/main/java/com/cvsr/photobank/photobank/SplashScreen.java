package com.cvsr.photobank.photobank;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;

/**
 * This class handles showing splash screen for 2500 ms when the application is launched
 * @author rohan
 * @since 2016-06-10.
 */
public class SplashScreen extends Activity {

    private static int SPLASH_TIME_OUT = 2500;
    private ProgressBar progressBar;
    private Handler handler;
    private Runnable runnable;
    SharedPreferences sharedPreferences;
    /**
     * Instance of Handler class is used to load the main screen after 2500 ms
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setBackgroundDrawable(null);
        progressBar = (ProgressBar)findViewById(R.id.splash_screen_progress);
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.text_white),android.graphics.PorterDuff.Mode.MULTIPLY);
        progressBar.setVisibility(View.VISIBLE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SplashScreen.this);
        runnable = new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(SplashScreen.this, MenuActivity.class));
                finish();
            }
        };
        handler = new Handler();
        handler.postDelayed(runnable, SPLASH_TIME_OUT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }
}