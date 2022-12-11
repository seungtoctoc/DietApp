package com.example.dietapp5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    TextView dtext_date, dtext_menu, dtext_num, dtext_review;
    Button btn_back;
    ImageView imageView;
    GoogleMap mMap;
    Double latitude, longitude;
    String detailAddr;
    MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dtext_date = findViewById(R.id.dtext_date);
        dtext_menu = findViewById(R.id.dtext_menu);
        dtext_num = findViewById(R.id.dtext_num);
        dtext_review = findViewById(R.id.dtext_review);
        imageView = findViewById(R.id.imageView2);
        mapView = findViewById(R.id.mapView);

        mapView.getMapAsync(this);
        mapView.onCreate(savedInstanceState);



        Intent getIntent = getIntent();
//        입력
        if(getIntent.hasExtra("position")){
            int position = getIntent.getIntExtra("position",0);
            Meal thisMeal = MainActivity.mealList.get(position);


            dtext_date.setText(thisMeal.getDate() + thisMeal.getTime());
            dtext_menu.setText(thisMeal.getMenu());
            dtext_num.setText(thisMeal.getDetailKcal());
            dtext_review.setText(thisMeal.review);

            if(thisMeal.img==null){
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.dongguk));
            }
            else{
                imageView.setImageBitmap(thisMeal.img);
            }

            longitude = thisMeal.getLongitude();
            latitude = thisMeal.getLatitude();
            detailAddr = thisMeal.detailAddr;
        }

//      이전
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent4 = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent4);
            }
        });


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latlng = new LatLng(latitude, longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        MarkerOptions markerOptions = new MarkerOptions().position(latlng).title(detailAddr);
        Marker marker = mMap.addMarker(markerOptions);
        marker.showInfoWindow();
    }

    @Override
    public void onStart(){
        super.onStart();
        mapView.onStart();
    }


}