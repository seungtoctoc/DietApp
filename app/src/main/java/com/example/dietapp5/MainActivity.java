package com.example.dietapp5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView myRecyclerView;
    LinearLayoutManager myLayoutManager;
    FloatingActionButton fab;

    static ArrayList<Meal> mealList = new ArrayList<Meal>();
    static ArrayList<String> foodList = new ArrayList<>();
    static ArrayList<Integer> kcalList = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodDataSet();

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });



        if(mealList.size()==0){
            dataSampleSet();
        }

        myRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        myRecyclerView.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myLayoutManager);
        MyAdapter myAdapter = new MyAdapter();
        myRecyclerView.setAdapter(myAdapter);




    }

    void dataSampleSet(){
        Meal testMeal1 = new Meal("마라탕", 1);
        testMeal1.setDate(2022, 11, 12, 10, 30);
        testMeal1.setReview("에이 이걸 돈 받고 파는 건가요. 맛대가리 없어요");
        testMeal1.setKcal(450);
        mealList.add(testMeal1);

        Meal testMeal2 = new Meal("마라샹궈", 2);
        testMeal2.setReview("미친 평생 팔아주세요. 제발 부탁드릴게요");
        testMeal2.setDate(2022, 11, 20, 16, 30);
        testMeal2.setKcal(460);
        mealList.add(testMeal2);

        Meal testMeal3 = new Meal("꿔바로우", 1);
        testMeal3.setDate(2022, 11, 20, 18, 30);
        testMeal3.setKcal(640);
        mealList.add(testMeal3);

        Meal testMeal4 = new Meal("짜장면", 1);
        testMeal4.setDate(2022, 11, 21, 12, 30);
        testMeal4.setKcal(480);
        mealList.add(testMeal4);


    }

    void foodDataSet(){
        foodList.add("마라탕");
        kcalList.add(450);
        foodList.add("마라샹궈");
        kcalList.add(460);
        foodList.add("김치볶음밥");
        kcalList.add(446);
        foodList.add("꿔바로우");
        kcalList.add(640);
        foodList.add("짬뽕");
        kcalList.add(470);
        foodList.add("짜장면");
        kcalList.add(480);

    }

}