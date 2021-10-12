package com.code.competition_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class SecondActivity extends AppCompatActivity {

    ImageButton QR_code_BT,ChangeInfo_BT;
    long backKeyPressedTime = 0;
    private Toast toast;
    SharedPreferences shdprefs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkFirstRun();
        setContentView(R.layout.activity_second);

        QR_code_BT = findViewById(R.id.QR_code_bt);
        ChangeInfo_BT = findViewById(R.id.information_bt);

        ChangeInfo_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_view_Change = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent_view_Change);
            }
        });
        QR_code_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_view_Change = new Intent(getApplicationContext(),ScanActivity.class);
                startActivity(intent_view_Change);
            }
        });
        //첫 실행을 체크하기 위한 영역
        shdprefs = getSharedPreferences("isfirst?",MODE_PRIVATE);
    }
    public void checkFirstRun(){
        boolean isFirstRun = shdprefs.getBoolean("isfirst?",true);
        if (isFirstRun){
            Intent intent_view_Change = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent_view_Change);

            shdprefs.edit().putBoolean("isfirst?",false).apply();
        }
    }

    @Override
    public void onBackPressed() {
        //뒤로가기 버튼 두번 입력시 종료되는 코드
//        super.onBackPressed();
        if (System.currentTimeMillis() > backKeyPressedTime + 2000){
            backKeyPressedTime = System.currentTimeMillis();

            toast = Toast.makeText(getApplicationContext(),"'뒤로' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime +2000){
            ActivityCompat.finishAffinity(this);
            toast.cancel();
        }
    }
}
