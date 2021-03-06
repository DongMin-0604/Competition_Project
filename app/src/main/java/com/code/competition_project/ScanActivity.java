package com.code.competition_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
                Toast.makeText(this,"스캔 취소",Toast.LENGTH_SHORT).show();
                finish();
                //스캔 취소해도 넘어가게하기 (테스트용 코드)
//                    Toast.makeText(this,"스캔 완료",Toast.LENGTH_LONG).show();
//                    seatNumber = result.getContents();
//                     seatNumber = "10번";
//                    Intent intent_view_Change = new Intent(getApplicationContext(),Result_Activity.class);
//                    //세컨드 엑티비티로 값 넘기기
//                    intent_view_Change.putExtra("grade_Scan",grade);
//                    intent_view_Change.putExtra("class_Scan",class1);
//                    intent_view_Change.putExtra("number_Scan",number);
//                    intent_view_Change.putExtra("name_Scan",name);
//                    intent_view_Change.putExtra("seatNumber_Scan",seatNumber);
//                    startActivity(intent_view_Change);
            }else {
                if (result.getContents().contains("번")){
                    Toast.makeText(this,"스캔 완료",Toast.LENGTH_LONG).show();
                    seatNumber = result.getContents();
                    Intent intent_view_Change = new Intent(getApplicationContext(),Result_Activity.class);
                    //세컨드 엑티비티로 값 넘기기
                    intent_view_Change.putExtra("grade_Scan",grade);
                    intent_view_Change.putExtra("class_Scan",class1);
                    intent_view_Change.putExtra("number_Scan",number);
                    intent_view_Change.putExtra("name_Scan",name);
                    intent_view_Change.putExtra("seatNumber_Scan",seatNumber);
                    startActivity(intent_view_Change);
                }else {
                    Toast.makeText(this,"잘못된 QR코드 형식입니다.",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
