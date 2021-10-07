package com.code.competition_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner SP_grade,SP_class,SP_number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SP_grade = findViewById(R.id.SP_grade);
        SP_class = findViewById(R.id.SP_class);
        SP_number = findViewById(R.id.SP_number);

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

        //spinner 선택 시 이벤트
        SP_grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}