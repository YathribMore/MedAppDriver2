package com.magsood.medappuser.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.magsood.medappuser.R;
import com.magsood.medappuser.SharedPrefrense.UserPreferences;

public class Main extends AppCompatActivity {
LinearLayout hospital,pharmacy,lapotary;


    UserPreferences userPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        hospital=findViewById(R.id.hospitlis);
        pharmacy=findViewById(R.id.pharmaces);
        lapotary=findViewById(R.id.lapotery);
        userPreferences = new UserPreferences(this);
        pharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, MainActivity.class);
//                intent.putExtra("choice","ph");
                userPreferences.setChoice("ph");
                startActivity(intent);
            }
        });
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this,MainActivity.class);
//                intent.putExtra("choice","ho");
                userPreferences.setChoice("ho");
                startActivity(intent);
            }
        });
        lapotary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this,MainActivity.class);
//                intent.putExtra("choice","lap");
                userPreferences.setChoice("lap");
                startActivity(intent);
            }
        });
    }
}