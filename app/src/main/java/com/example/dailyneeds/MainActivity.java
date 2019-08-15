package com.example.dailyneeds;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SystemClock.sleep(3000);


        Intent loginIntent=new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(loginIntent);

        finish();
    }
}
