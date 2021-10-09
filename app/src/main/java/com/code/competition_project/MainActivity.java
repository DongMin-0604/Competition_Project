package com.code.competition_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner SP_grade,SP_class,SP_number;
    Button bt_next;
    EditText et_name;
    CheckBox cb_agree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SP_grade = findViewById(R.id.SP_grade);
        SP_class = findViewById(R.id.SP_class);
        SP_number = findViewById(R.id.SP_number);

        bt_next = findViewById(R.id.BT_next);
        et_name = findViewById(R.id.ET_name);
        cb_agree = findViewById(R.id.CB_agree);

        //array에 있던 학년 목록 값들을 List로 만듦
        List<String> array_grade = Arrays.asList(getResources().getStringArray(R.array.array_grade));
        List<String> array_class = Arrays.asList(getResources().getStringArray(R.array.array_class));
        List<String> array_number = Arrays.asList(getResources().getStringArray(R.array.array_number));

        //ArrayAdapter를 만들어서 학년 값을 XML에 스피너에 연결
        ArrayAdapter<String> adapter_grade = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,array_grade);
        adapter_grade.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        SP_grade.setAdapter(adapter_grade);

        ArrayAdapter<String> adapter_class = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,array_class);
        adapter_class .setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        SP_class.setAdapter(adapter_class);

        ArrayAdapter<String> adapter_number = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,array_number);
        adapter_number.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        SP_number.setAdapter(adapter_number);

        //'다음으로' 버튼 영역
        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_name.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"잘못된 입력입니다.",Toast.LENGTH_SHORT).show();
                }else if (!cb_agree.isChecked()){
                    Toast.makeText(getApplicationContext(),"개인정보 수집에 동의해주세요.",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent_view_Change = new Intent(getApplicationContext(),SecondActivity.class);
                    startActivity(intent_view_Change);
                }

            }
        });


    }
}