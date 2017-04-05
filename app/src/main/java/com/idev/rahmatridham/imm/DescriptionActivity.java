package com.idev.rahmatridham.imm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.idev.rahmatridham.himaifofficialapps.R;

public class DescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
