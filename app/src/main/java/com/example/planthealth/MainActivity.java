package com.example.planthealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText name_et, age_et, height_et, weight_et;
    Spinner spinner;
    RadioGroup radioGroup;
    RadioButton  radioButton;
    String nameSent, activitySent, gender;
    Integer ageSent, radioID;
    Float heightSent, weightSent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Welcome"); //edit textTitle to Welcome

        spinner = (Spinner) findViewById(R.id.spinner);
        radioGroup = (RadioGroup) findViewById(R.id.genderRadioGroup);

        name_et = (EditText) findViewById(R.id.name_et);
        age_et = (EditText) findViewById(R.id.age_et);
        height_et = (EditText) findViewById(R.id.height_et);
        weight_et = (EditText) findViewById(R.id.weight_et);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.activity_item, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    //press Button send Data to Home
    public void buttonSenderData(View view){

        //Check data editText
        if (TextUtils.isEmpty(name_et.getText().toString())){
            name_et.setError("กรุณากรอกชื่อ");
            return;
        }
        if (TextUtils.isEmpty(age_et.getText().toString())){
            age_et.setError("กรุณากรอกอายุ");
            return;
        }
        if (Integer.valueOf(age_et.getText().toString()) > 118 || Integer.valueOf(age_et.getText().toString()) < 1){
            age_et.setError("กรุณากรอกข้อมูลตามความจริง");
            return;
        }
        if (TextUtils.isEmpty(height_et.getText().toString())){
            height_et.setError("กรุณากรอกส่วนสูง");
            return;
        }
        if (Float.valueOf(height_et.getText().toString()) > 272 || Float.valueOf(height_et.getText().toString()) < 67.08){
            height_et.setError("กรุณากรอกข้อมูลตามความจริง");
            return;
        }
        if (TextUtils.isEmpty(weight_et.getText().toString())){
            weight_et.setError("กรุณากรอกน้ำหนัก");
            return;
        }
        if (Float.valueOf(weight_et.getText().toString()) > 463 || Float.valueOf(weight_et.getText().toString()) < 1){
            weight_et.setError("กรุณากรอกข้อมูลตามความจริง");
            return;
        }
        //Show Text When Successful filling
        Toast.makeText(MainActivity.this, "Succesfull", Toast.LENGTH_SHORT).show();

        radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);

        gender = radioButton.getText().toString();
        nameSent = name_et.getText().toString();
        ageSent = Integer.valueOf(age_et.getText().toString());
        heightSent = Float.valueOf(height_et.getText().toString());
        weightSent = Float.valueOf(weight_et.getText().toString());
        activitySent = spinner.getSelectedItem().toString();

        //Function send data to Home
        Intent sender = new Intent(MainActivity.this, Home.class);

        sender.putExtra("nameUser", nameSent);
        sender.putExtra("ageUser", ageSent);
        sender.putExtra("heightUser", heightSent);
        sender.putExtra("weightUser", weightSent);
        sender.putExtra("activityUser", activitySent);
        sender.putExtra("gender", gender);
        startActivity(sender);

    }



}