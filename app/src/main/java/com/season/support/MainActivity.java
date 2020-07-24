package com.season.support;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.season.lib.BaseContext;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BaseContext.init(this);
    }
}
