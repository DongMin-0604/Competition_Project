package com.code.competition_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    ImageView IV_logo;
    Animation animFadein;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        IV_logo = findViewById(R.id.iv_logo);

        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        IV_logo.startAnimation(animFadein);
        IV_logo.setImageResource(R.drawable.splash_img2);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(intent);
                finish();

            }
        },2000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
