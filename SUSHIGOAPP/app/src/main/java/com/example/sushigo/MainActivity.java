package com.example.sushigo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    ImageView imageView;
    ImageView imageCart;
    ImageView imageProfile;
    CircleImageView circleImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circleImageView = findViewById(R.id.aktif);
        circleImageView.setOnClickListener(view -> startActivity (new Intent(MainActivity.this, ListMakanan.class)));

        imageView = findViewById(R.id.imglist);
        imageView.setOnClickListener(view -> startActivity (new Intent(MainActivity.this, ListMakanan.class)));

        imageCart = findViewById(R.id.cart);
        imageCart.setOnClickListener(view -> startActivity (new Intent(MainActivity.this, SummaryActivity.class)));

        imageProfile = findViewById(R.id.imgprofile);
        imageProfile.setOnClickListener(view -> startActivity (new Intent(MainActivity.this, activity_user.class)));

    }

    @Override
    public void onClick(View view) {

    }
}