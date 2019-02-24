package com.jianyiclub.jianzhi.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jianyiclub.jianzhi.R;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences sharedPreferences = getSharedPreferences("test", MODE_PRIVATE);
        //得到SharedPreferences.Editor对象，并保存数据到该对象中
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(sharedPreferences.getInt("DON",0)==0){
            editor.putInt("DON",10);
            editor.commit();
        }
        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("DayOrNight",sharedPreferences.getInt("DON",0));
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
