package com.code.competition_project;

import android.content.Intent;
import android.os.Bundle;
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

        getinfo();

        qrScan = new IntentIntegrator(this);
        qrScan.setOrientationLocked(false);
        qrScan.setPrompt("QR코드를 스캔해 주세요");
        qrScan.initiateScan();
    }
    public void getinfo(){
        Intent intent_getinfo = getIntent();
        grade = intent_getinfo.getStringExtra("grade");
        class1 = intent_getinfo.getStringExtra("class");
        number = intent_getinfo.getStringExtra("number");
        name = intent_getinfo.getStringExtra("name");;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result != null){
            if (result.getContents() == null){


            }else {
                Toast.makeText(this,"스캔 완료 " + result.getContents(),Toast.LENGTH_LONG).show();
                Intent intent_view_Change = new Intent(getApplicationContext(),Result_Activity.class);
                intent_view_Change.putExtra("grade",grade);
                intent_view_Change.putExtra("class",class1);
                intent_view_Change.putExtra("number",number);
                intent_view_Change.putExtra("name",name);
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
