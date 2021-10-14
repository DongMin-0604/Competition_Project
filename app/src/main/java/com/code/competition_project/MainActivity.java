package com.code.competition_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
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
    private Toast toast;
    boolean Update_Check = true;
    String grade_text,class_text,number_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent_FLAG = new Intent();
        intent_FLAG.addFlags(intent_FLAG.FLAG_ACTIVITY_CLEAR_TOP);

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
        ArrayAdapter<String> adapter_grade = new ArrayAdapter<String>(this,R.layout.custom_spinner,array_grade);
        adapter_grade.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        SP_grade.setAdapter(adapter_grade);

        ArrayAdapter<String> adapter_class = new ArrayAdapter<String>(this,R.layout.custom_spinner,array_class);
        adapter_class .setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        SP_class.setAdapter(adapter_class);

        ArrayAdapter<String> adapter_number = new ArrayAdapter<String>(this,R.layout.custom_spinner,array_number);
        adapter_number.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        SP_number.setAdapter(adapter_number);



        //'다음으로' 버튼 영역
        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_name.getText().toString().equals("")){
                    toast.makeText(getApplicationContext(),"잘못된 입력입니다.",Toast.LENGTH_SHORT).show();
                }else if (!cb_agree.isChecked()){
                    toast.makeText(getApplicationContext(),"개인정보 수집에 동의해주세요.",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent_view_Change = new Intent(getApplicationContext(),SecondActivity.class);
                    //Spinner 값 가져오기
                    grade_text = SP_grade.getSelectedItem().toString();
                    class_text = SP_class.getSelectedItem().toString();
                    number_text = SP_number.getSelectedItem().toString();
                    //세컨드 엑티비티로 입력값 전달
                    intent_view_Change.putExtra("grade",grade_text);
                    intent_view_Change.putExtra("class",class_text);
                    intent_view_Change.putExtra("number",number_text);
                    intent_view_Change.putExtra("name",et_name.getText().toString());
                    intent_view_Change.putExtra("Update",Update_Check);
                    startActivity(intent_view_Change);
                }

            }
        });

    }


    @Override
    public void onBackPressed() {
        //뒤로가기 버튼 두번 입력시 종료되는 코드
//        super.onBackPressed();
            toast = Toast.makeText(getApplicationContext(),"정보 입력창에서는 앱을 종료하실 수 없습니다.", Toast.LENGTH_SHORT);
            toast.show();
    }
}