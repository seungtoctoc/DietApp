package com.example.dietapp5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

public class Meal {
    String menu;
    int num;
    int y=2022, mo=12, d=12, h=14, mi=30;
    String review="맛있어요", longitude="126.998425", latitude="37.55827", detailAddr="신공학관";
    Bitmap img;
    int kcal=510;


    public Meal(String menu, int num){
        this.menu = menu;
        this.num = num;
    }

    public void setAddr(String longitude, String latitude, String detailAddr){
        this.longitude = longitude;
        this.latitude = latitude;
        this.detailAddr = detailAddr;
    }

    public double getLongitude(){
        return Double.parseDouble(longitude);
    }

    public double getLatitude(){
        return Double.parseDouble(latitude);
    }

    public void setKcal(int kcal){
        this.kcal = kcal;
    }

    public String getKcal(){
        return Integer.toString(kcal) + " kcal";
    }

    public String getTotalKcal(){
        return Integer.toString(kcal*num) + " kcal";
    }

    public String getDetailKcal(){
        return Integer.toString(kcal*num) + " kcal (" + Integer.toString(kcal) + " kcal * " + Integer.toString(num) + ")";
    }

    public void setDate(int y, int mo, int d, int h, int mi){
        this.y = y;
        this.mo = mo;
        this.d = d;
        this.h = h;
        this.mi = mi;
    }

    public void setImg(Bitmap img){
        this.img = img;
    }

    public void setReview(String review){
        this.review = review;
    }

    public String getDate(){
        return Integer.toString(y) + "년 " + Integer.toString(mo) + "월 " + Integer.toString(d) + "일 ";
    }

    public String getTime(){
        return Integer.toString(h) + "시 " + Integer.toString(mo) + "분 ";
    }

    public String getMenu(){
        return menu + " " + Integer.toString(num) + "그릇";
    }

    public String getNum(){
        return Integer.toString(num) + "그릇";
    }


}
