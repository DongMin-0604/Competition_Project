package com.code.competition_project;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class SecondActivity extends AppCompatActivity {

    ImageButton QR_code_BT,ChangeInfo_BT;
    long backKeyPressedTime = 0;
    private Toast toast;
    boolean Update_Check;
    String grade,class1,number,name,seatNumber,time;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkFirstRun();
        setContentView(R.layout.activity_second);
        QR_code_BT = findViewById(R.id.QR_code_bt);
        ChangeInfo_BT = findViewById(R.id.information_bt);
        getResult();

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
                //스캔 엑티비티로 값 전달
                intent_view_Change.putExtra("grade_Second",grade);
                intent_view_Change.putExtra("class_Second",class1);
                intent_view_Change.putExtra("number_Second",number);
                intent_view_Change.putExtra("name_Second",name);
                startActivity(intent_view_Change);
                //버튼 클릭시 이미지 변경
                QR_code_BT.setImageResource(R.drawable.img_bt_on);
                //변경된 이미지 1초뒤 원상 복구
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        QR_code_BT.setImageResource(R.drawable.img_bt);
                    }
                },1000);
            }
        });
    }
    public void getResult(){
        //메인에서 값 받아오기
        Intent intent = getIntent();
        Update_Check = intent.getBooleanExtra("Update",false);
        grade = intent.getStringExtra("grade");
        class1 = intent.getStringExtra("class");
        number = intent.getStringExtra("number");
        name = intent.getStringExtra("name");

        //값 저장을 위해 sharedPreferences 불러오기
        sharedPreferences = getSharedPreferences("value1",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //수정을 했는지 체크를 위한 if문
        if (Update_Check == false){
            //수정을 하지 않았을 경우 기존값 표시 후 기존값 저장
            grade = sharedPreferences.getString("saveGrade",intent.getStringExtra("grade"));
            class1 = sharedPreferences.getString("saveClass",intent.getStringExtra("grade"));
            number = sharedPreferences.getString("saveNumber",intent.getStringExtra("grade"));
            name = sharedPreferences.getString("saveName",intent.getStringExtra("grade"));
            editor.putString("saveGrade",grade);
            editor.putString("saveClass",class1);
            editor.putString("saveNumber",number);
            editor.putString("saveName",name);
            editor.apply();
            editor.commit();
        }else if (Update_Check == true){
            //수정을 했을때 값 다시 저장하고 표시
            editor.putString("saveGrade",intent.getStringExtra("grade"));
            editor.putString("saveClass",intent.getStringExtra("class"));
            editor.putString("saveNumber",intent.getStringExtra("number"));
            editor.putString("saveName",intent.getStringExtra("name"));
            editor.apply();
            editor.commit();
            grade = sharedPreferences.getString("saveGrade", intent.getStringExtra("grade"));
            class1 = sharedPreferences.getString("saveClass",intent.getStringExtra("class"));
            number = sharedPreferences.getString("saveNumber",intent.getStringExtra("number"));
            name = sharedPreferences.getString("saveName",intent.getStringExtra("name"));
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
