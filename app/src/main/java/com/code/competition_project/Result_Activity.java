package com.code.competition_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Result_Activity extends AppCompatActivity {
    long backKeyPressedTime = 0;
    private Toast toast;
    TextView tv_grade,tv_class,tv_number,tv_name,tv_seatNumber,tv_time;
    String grade,class1,number,name,seatNumber,time;
    ImageButton bt_next;
    long mNow;
    Date mDate;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    //파이어베이스 전송 코드 영역
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_check);
        init();
        getResult();
        setTextView();
        SendToFireBase(grade,class1,seatNumber,name);

        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_view_Change = new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(intent_view_Change);
            }
        });

    }
    public void setTextView(){
        //텍스트 뷰에 값 넣기
        tv_grade.setText(grade);
        tv_class.setText(class1);
        tv_number.setText(number);
        tv_name.setText(name);
        tv_seatNumber.setText(seatNumber);
        tv_time.setText(getTime());
    }
    public void getResult(){
        //스캔 엑티비티에서 값 받아오기
        Intent intent = getIntent();
        grade = intent.getStringExtra("grade_Scan");
        class1 = intent.getStringExtra("class_Scan");
        number = intent.getStringExtra("number_Scan");
        name = intent.getStringExtra("name_Scan");
        seatNumber = intent.getStringExtra("seatNumber_Scan");
    }
    public void init(){
        //기본 세팅
        tv_grade = findViewById(R.id.TV_grade);
        tv_class = findViewById(R.id.TV_class);
        tv_number = findViewById(R.id.TV_number);
        tv_name = findViewById(R.id.TV_name);
        tv_seatNumber = findViewById(R.id.TV_seat_number);
        bt_next = findViewById(R.id.BT_next);
        tv_time = findViewById(R.id.TV_time);

    }
    private String getTime(){
        //현재 시각 받아오기
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);

        return format.format(mDate);
    }
    public void SendToFireBase(String grade,String class1,String seatNumber,String name){
        //파이어베이스 전송 코드 영역
        databaseReference.child(getTime()).child(grade).child(class1).child(number+" "+name).child("SeatNumber").setValue(seatNumber);
        //파이어베이스 값 받아오기 위한 수정 코드 영역(이슈없음)
//        SendToFirebase sendToFirebase = new SendToFirebase(grade,class1,seatNumber,name);
//        databaseReference.child(getTime()).child(name).setValue(sendToFirebase);
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
            toast.cancel();
        }
    }
}
