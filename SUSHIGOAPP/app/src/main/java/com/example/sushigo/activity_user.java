package com.example.sushigo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class activity_user extends AppCompatActivity implements View.OnClickListener{
    ImageView imageView;
    ImageView imageHome;
    Button button;
    private Button btnDialPhone;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        imageView = findViewById(R.id.imglist);
        imageView.setOnClickListener(view -> startActivity (new Intent(activity_user.this, ListMakanan.class)));

        imageHome = findViewById(R.id.imghome);
        imageHome.setOnClickListener(view -> startActivity(new Intent(activity_user.this, MainActivity.class)));

        button = findViewById(R.id.btnLogout);
        button.setOnClickListener(view -> startActivity(new Intent(activity_user.this, OnBoarding.class)));

        btnDialPhone = (Button) findViewById(R.id.btnHub);
        btnDialPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnHub:
                String phoneNumber="0812123152";
                Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                startActivity(dialPhoneIntent);
                break;
        }
    }
}