package com.code.competition_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanActivity extends AppCompatActivity {
    private IntentIntegrator qrScan;
    long backKeyPressedTime = 0;
    private Toast toast;
    String temp;
    String grade,class1,number,name,seatNumber,time;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        qrScan = new IntentIntegrator(this);
        qrScan.setOrientationLocked(false);
        qrScan.setPrompt("QR코드를 스캔해 주세요");
        qrScan.initiateScan();

        getResult();
    }

    public void getResult(){
        //세컨드 엑티비티에서 값 받아오기
        Intent intent = getIntent();
        grade = intent.getStringExtra("grade_Second");
        class1 = intent.getStringExtra("class_Second");
        number = intent.getStringExtra("number_Second");
        name = intent.getStringExtra("name_Second");


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result != null){
            if (result.getContents() == null){


            }else {
                Toast.makeText(this,"스캔 완료 " + result.getContents(),Toast.LENGTH_LONG).show();
                seatNumber = result.getContents();
                Intent intent_view_Change = new Intent(getApplicationContext(),Result_Activity.class);
                //세컨드 엑티비티로 값 넘기기
                intent_view_Change.putExtra("grade_Scan",grade);
                intent_view_Change.putExtra("class_Scan",class1);
                intent_view_Change.putExtra("number_Scan",number);
                intent_view_Change.putExtra("name_Scan",name);
                intent_view_Change.putExtra("seatNumber_Scan",seatNumber);
                startActivity(intent_view_Change);
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (System.currentTimeMillis() > backKeyPressedTime + 2000){
            backKeyPressedTime = System.currentTimeMillis();

            toast = Toast.makeText(getApplicationContext(),"'뒤로' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime +2000){
            ActivityCompat.finishAffinity(this);
//            finish();
            toast.cancel();
        }
    }
}
