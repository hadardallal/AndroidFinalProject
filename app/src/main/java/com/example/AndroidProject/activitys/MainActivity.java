package com.example.AndroidProject.activitys;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.example.AndroidProject.R;

public class MainActivity extends AppCompatActivity
{
    private Intent musicServiceIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
   protected void onDestroy() {

        super.onDestroy();
    }


}
