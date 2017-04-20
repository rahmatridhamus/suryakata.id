package com.idev.rahmatridham.imm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.idev.rahmatridham.imm.IntroActivity.IntroActivity;


public class Splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                SharedPreferences pref = getSharedPreferences("ActivityPREF", Splashscreen.this.getBaseContext().MODE_PRIVATE);

                if (pref.getBoolean("activity_executed", true)) {
                    Intent intent = new Intent(Splashscreen.this, IntroActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(Splashscreen.this, LandingPage.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 2000);
    }
}
