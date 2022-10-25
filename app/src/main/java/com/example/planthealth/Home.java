package com.example.planthealth;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Home extends AppCompatActivity {

    private TextView nameText, textView_kcal, textBMI, textWeight, textHeight, textFoodCurr, textFoodKcalCurr;
    private ImageView imgUser;
    private String nameUser, activity, genderUser, totalTDEE, totalBMI, foodUser, kcalUser;
    private Integer currCal = 0;
    private Integer ageUser;
    private Float heightUser, weightUser;
    private double BMR, BMI, TDEE, activity_cons;
    private ProgressBar progressBar_kcal;
    private EditText getfoodUser, getkcalUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Function Format Decimal
        DecimalFormat df = new DecimalFormat("#");
        DecimalFormat df2 = new DecimalFormat("#.##");

        nameText = (TextView) findViewById(R.id.textView_name);
        imgUser = (ImageView) findViewById(R.id.imageUser);
        textView_kcal = (TextView) findViewById(R.id.textView_kcal);
        textBMI = (TextView) findViewById(R.id.textBMI);
        textHeight = (TextView) findViewById(R.id.textHeight);
        textWeight = (TextView) findViewById(R.id.textWeight);
        progressBar_kcal = (ProgressBar) findViewById(R.id.progressBar_kcal);
        getfoodUser = (EditText) findViewById(R.id.foodUser);
        getkcalUser = (EditText) findViewById(R.id.kcalUser);
        textFoodCurr = (TextView) findViewById(R.id.textFoodCurr);
        textFoodKcalCurr = (TextView) findViewById(R.id.textFoodKcalCurr);

        // get value from MainActivity
        Intent i = getIntent();
        nameUser = i.getStringExtra("nameUser");
        ageUser = i.getIntExtra("ageUser", 0);
        heightUser = i.getFloatExtra("heightUser", 0);
        weightUser = i.getFloatExtra("weightUser", 0);
        genderUser = i.getStringExtra("gender");
        activity = i.getStringExtra("activityUser");

        // convert textactivity to duble value
        switch (activity){
            case "ไม่ออกกำลังกายหรืออกกำลังกายน้อยมาก":
                activity_cons = 1.2;
                break;
            case "ออกกำลังกายน้อยเล่นกีฬา 1-3 วัน/สัปดาห์":
                activity_cons = 1.375;
                break;
            case "ออกกำลังกายปานกลางเล่นกีฬา 3-5 วัน/สัปดาห์":
                activity_cons = 1.55;
                break;
            case "ออกกำลังกายหนักเล่นกีฬา 6-7 วัน/สัปดาห์":
                activity_cons = 1.725;
                break;
            case "ออกกำลังกายหนักมากเป็นนักกีฬา":
                activity_cons = 1.9;
                break;
        }

        // calculate BMR, TDEE classify gender
        switch (genderUser){
            case "male":
                BMR = 66 + (13.7 * weightUser) + (5 * heightUser) - (6.8 * ageUser);
                TDEE = BMR * activity_cons;
                imgUser.setImageResource(R.drawable.ic_male_home);
                break;
            case "female":
                BMR = 665 + (9.6 * weightUser) + (1.8 * heightUser) - (4.7 * ageUser);
                TDEE = BMR * activity_cons;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + genderUser);
        }
        //calculate BMI
        BMI = weightUser / Math.pow((heightUser*0.01), 2);
        //convert format Decimal
        totalTDEE = df.format(TDEE);
        totalBMI = df2.format(BMI);


        nameText.setText(String.valueOf(nameUser));
        textView_kcal.setText(String.valueOf(currCal + " / " + totalTDEE + " kcal"));
        progressBar_kcal.setProgress(currCal);
        progressBar_kcal.setMax(Integer.parseInt(totalTDEE));
        textHeight.setText(String.valueOf(heightUser));
        textWeight.setText(String.valueOf(weightUser));
        textBMI.setText(String.valueOf(totalBMI));

    }

    //press Button to FoodItem
    public void buttonSendertoFooditem(View view){

        Intent sender = new Intent(Home.this, foodActivity.class);
        startActivity(sender);
    }

    //press Button Add Food && Kcal
    public void addFooditem(View view){

        //Check
        if (TextUtils.isEmpty(getfoodUser.getText().toString())){
            getfoodUser.setError("กรุณากรอกอาหารที่รับประทาน");
            return;
        }
        if (TextUtils.isEmpty(getkcalUser.getText().toString())){
            getkcalUser.setError("กรุณากรอกจำนวนแคลอที่ของอาหาร");
            return;
        }

        // Keeps EditText food, Kcal to variable
        foodUser = getfoodUser.getText().toString();
        kcalUser = getkcalUser.getText().toString();
        currCal = currCal + Integer.valueOf(kcalUser);

        textFoodCurr.setText(foodUser);
        textFoodKcalCurr.setText(kcalUser + " Kcal");
        textView_kcal.setText(String.valueOf(currCal + " / " + totalTDEE + " kcal"));
        progressBar_kcal.setProgress(currCal);

        // Set Color ProgressBar When currCal >
        if (currCal >= Integer.parseInt(totalTDEE)){
            progressBar_kcal.setProgressDrawable(getResources().getDrawable(R.drawable.custom_progress_bgmax));
        }
        Toast.makeText(Home.this, "เพิ่มข้อมูลแล้ว", Toast.LENGTH_SHORT).show(); //Show Text When Successful filling

        getfoodUser.getText().clear();
        getkcalUser.getText().clear();
        getfoodUser.clearFocus();
        getkcalUser.clearFocus();

    }

}