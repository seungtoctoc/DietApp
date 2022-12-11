package com.example.dietapp5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.InputStream;
import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    int menu1Num = 1;
    Button btn_img, btn_back2, btn_save;
    ImageView imageView;
    TextView text_date, text_time, text_loc;
    EditText edit_menu, edit_review, edit_addr;
    int y, mo, d, h, mi;
    Bitmap img;
    String latitude, longitude; // 경도

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        text_date = findViewById(R.id.text_date);
        text_time = findViewById(R.id.text_time);
        imageView = findViewById(R.id.imageView);
        btn_img = findViewById(R.id.btn_img);
        btn_back2= findViewById(R.id.btn_back2);
        btn_save = findViewById(R.id.btn_save);
        edit_menu = findViewById(R.id.edit_menu);
        edit_review = findViewById(R.id.edit_review);
        text_loc = findViewById(R.id.text_loc);
        edit_addr = findViewById(R.id.edit_addr);

//        구글맵
        text_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(AddActivity.this, MapsActivity.class);
                startActivityForResult(intent4,2);
            }
        });


//        이미지
        btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });

//      날짜
        text_date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showDate();
            }
        });

//      시간
        text_time.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showTime();
            }
        });

//      이전
        btn_back2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent3 = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent3);
            }
        });


//      저장
        btn_save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String newMenu = edit_menu.getText().toString();
                String newReview = edit_review.getText().toString();
                Meal newMeal = new Meal(newMenu, menu1Num);
                newMeal.setDate(y, mo, d, h, mi);
                newMeal.setReview(newReview);
                newMeal.setImg(img);
                int index = MainActivity.foodList.indexOf(newMenu);
                if(index>-1){
                    newMeal.setKcal(MainActivity.kcalList.get(index));
                }
                newMeal.setAddr(longitude, latitude, edit_addr.getText().toString());


                MainActivity.mealList.add(newMeal);


                Intent intent2 = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent2);
            }
        });


    }

//  날짜
    void showDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                y = year;
                mo = month+1;
                d = dayOfMonth;
                text_date.setText(Integer.toString(y)+"년 " + Integer.toString(mo)+"월 " + Integer.toString(d)+"일");
            }
        },2022, 11, 12);

//        datePickerDialog.setMessage("메시지");
        datePickerDialog.show();
    }

//    시간
    void showTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                h = hourOfDay;
                mi = minute;
                if(h>12){
//                    오후
                    text_time.setText("오후 " + Integer.toString(h-12)+"시 " + Integer.toString(mi)+"분");
                }
                else{
                    text_time.setText("오전" + Integer.toString(h)+"시 " + Integer.toString(mi)+"분");
                }

            }
        }, 12, 00, false);

//        timePickerDialog.setMessage("메시지");
        timePickerDialog.show();
    }



//    수량
    public void plusOrder(View view) {
        menu1Num++;
        displayMenu1Num(menu1Num);
    }
    public void reduceOrder(View view) {
        if (menu1Num <= 1) {
            menu1Num = 1;
            Toast.makeText(this.getApplicationContext(), "이미 가장 낮은 수량입니다.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        menu1Num--;
        displayMenu1Num(menu1Num);
    }
    public void displayMenu1Num(int num) {
        TextView quantityView = findViewById(R.id.quantity_view1);
        quantityView.setText(String.valueOf(num));
    }

//    이미지
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    try {
                        InputStream in = getContentResolver().openInputStream(data.getData());
                        img = BitmapFactory.decodeStream(in);
                        in.close();
                        imageView.setImageBitmap(img);
                    }
                    catch(Exception e){

                    }

                }
                break;
            case 2:
                if (resultCode == RESULT_OK){
                    longitude = data.getStringExtra("long");
                    latitude = data.getStringExtra("lat");
                    text_loc.setText("장소 입력 완료");
                }
        }
    }



}