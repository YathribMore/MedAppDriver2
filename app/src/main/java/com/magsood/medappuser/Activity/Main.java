package com.magsood.medappuser.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.magsood.medappuser.R;

public class Main extends AppCompatActivity {
LinearLayout hospital,pharmacy,lapotary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        hospital=findViewById(R.id.hospitlis);
        pharmacy=findViewById(R.id.pharmaces);
        lapotary=findViewById(R.id.lapotery);
        pharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, MainActivity.class);
                startActivity(intent);
            }
        });
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this,HospitalMainActivity.class);
                startActivity(intent);
            }
        });
        lapotary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this,LapoteryMainActivity.class);
                startActivity(intent);
            }
        });
    }
}