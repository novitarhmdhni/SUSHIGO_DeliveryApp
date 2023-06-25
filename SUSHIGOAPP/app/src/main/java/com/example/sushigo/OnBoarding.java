package com.example.sushigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class OnBoarding extends AppCompatActivity implements View.OnClickListener {
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        btnlogin = (Button) findViewById(R.id.btn1);
        btnlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
            Intent login = new Intent(OnBoarding.this, Login_SushiGo.class);
            startActivity(login);
        }
    }