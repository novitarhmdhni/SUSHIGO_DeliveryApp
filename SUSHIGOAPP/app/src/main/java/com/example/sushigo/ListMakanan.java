package com.example.sushigo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ListMakanan extends AppCompatActivity {
    List<Model> modelList;
    RecyclerView recyclerView;
    OrderAdapter mAdapter;
    ImageView imageHome;
    ImageView imageProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_makanan);
        // creating an arraylist

        modelList = new ArrayList<>();
        modelList.add(new Model("Spicy Ikura Roll", getString(R.string.spicyikuraroll), R.drawable.image1));
        modelList.add(new Model("Salmon Ebi Roll", getString(R.string.salmonebi), R.drawable.image2));
        modelList.add(new Model("Wagyu Truffle Roll", getString(R.string.orangesmoothie), R.drawable.image3));
        modelList.add(new Model("Tuna Sashimi Roll", getString(R.string.orangevanilla), R.drawable.image4));
        modelList.add(new Model("Mayo Crispy Roll", getString(R.string.cappcuni), R.drawable.image5));
        modelList.add(new Model("Salmon Sashimi", getString(R.string.thaitea), R.drawable.image6));
        modelList.add(new Model("Crispy Tofu", getString(R.string.tea), R.drawable.image7));
        modelList.add(new Model("Katsu Cream", getString(R.string.bubbletea), R.drawable.image8));
        modelList.add(new Model("Crispy Mentai", getString(R.string.match), R.drawable.image9));

        // recyclerview
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        // adapter
        mAdapter = new OrderAdapter(this, modelList);
        recyclerView.setAdapter(mAdapter);

        imageHome = findViewById(R.id.imghome);
        imageHome.setOnClickListener(view -> startActivity (new Intent(ListMakanan.this, MainActivity.class)));

        imageProfile = findViewById(R.id.imgprofile);
        imageProfile.setOnClickListener(view -> startActivity (new Intent(ListMakanan.this, activity_user.class)));
    }
}