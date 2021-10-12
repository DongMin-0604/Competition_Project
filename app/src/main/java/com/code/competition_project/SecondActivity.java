package com.code.competition_project;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
    String grade,class1,number,name,seatNumber,time;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkFirstRun();
        setContentView(R.layout.activity_second);
        getinfo();
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
                intent_view_Change.putExtra("grade",grade);
                intent_view_Change.putExtra("class",class1);
                intent_view_Change.putExtra("number",number);
                intent_view_Change.putExtra("name",name);
                startActivity(intent_view_Change);
            }
        });
    }
    public void getinfo(){
        Intent intent_getinfo = getIntent();
        grade = intent_getinfo.getStringExtra("grade");
        class1 = intent_getinfo.getStringExtra("class");
        number = intent_getinfo.getStringExtra("number");
        name = intent_getinfo.getStringExtra("name");;

    }
    public void checkFirstRun(){
        SharedPreferences pref = getSharedPreferences("isFirst", Activity.MODE_PRIVATE);
        boolean first = pref.getBoolean("isFirst", false);
        if(first == false){
            Log.d("Is first Time?", "first");
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isFirst",true);
            editor.commit();
            //앱 최초 실행시 하고 싶은 작업
            Intent intent_view_Change = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent_view_Change);
        }else{
            Log.d("Is first Time?", "not first");
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
