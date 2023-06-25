package com.example.sushigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PemesanActivity extends AppCompatActivity {

    public static final String URL = "http://192.168.1.9/";
    private EditText editTextNama;
    private EditText editTextTelp;
    private EditText editTextAlamat;
    private EditText editTextDetail;
    private EditText editTextCatatan;
    private Button btnPesan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesan);
        editTextNama = findViewById(R.id.nm);
        editTextTelp = findViewById(R.id.tlp);
        editTextAlamat = findViewById(R.id.almt);
        editTextDetail = findViewById(R.id.dtl);
        editTextCatatan = findViewById(R.id.cat);
        btnPesan = findViewById(R.id.btnOrder);
        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = editTextNama.getText().toString();
                String telepon = editTextTelp.getText().toString();
                String alamat = editTextAlamat.getText().toString();
                String detail = editTextDetail.getText().toString();
                String catatan = editTextCatatan.getText().toString();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                RegisterAPI api = retrofit.create(RegisterAPI.class);
                Call<Value> call = api.daftar(nama, telepon, alamat, detail, catatan);
                call.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        String value = response.body().getValue();
                        String message = response.body().getMessage();
                        if (value.equals("1")) {
                            Toast.makeText(PemesanActivity.this, message, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PemesanActivity.this, OrderSuccess.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(PemesanActivity.this, message, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PemesanActivity.this, OrderSuccess.class);
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {
                        Toast.makeText(PemesanActivity.this, "Jaringan Error!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}