package com.example.sushigo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class Login_SushiGo extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvRegister;

    private Dbase_SushiGo dbase;
    public static final String SHARED_PREF_NAME = "myPref";

    private SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sushi_go);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register.newInstance().show(getSupportFragmentManager(), Register.TAG);
            }
        });

        dbase = new Dbase_SushiGo(this);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String getEmail = etEmail.getText().toString();
                String getPassword = etPassword.getText().toString();

                if (getPassword.isEmpty() && getPassword.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Email or Password is incorrect!", Toast.LENGTH_LONG).show();
                }else if(getEmail.equalsIgnoreCase("admin@sushigo.id")) {
                    Boolean masuk = dbase.checkLogin(getEmail, getPassword);
                    if (masuk == true) {
                        Boolean updateSession = dbase.upgradeSession("ada", 1);
                        if (updateSession == true) {
                            Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putBoolean("masuk", true);
                            editor.apply();
                            Intent admin = new Intent(getApplicationContext(), activity_admin.class);
                            startActivity(admin);
                            finish();
                        }
                    }
                }else{
                    Boolean masuk = dbase.checkLogin(getEmail, getPassword);
                    if (masuk == true){
                        Boolean updateSession = dbase.upgradeSession("ada", 1);
                        if (updateSession == true){
                            Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putBoolean("masuk", true);
                            editor.apply();
                            Intent dashbord = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(dashbord);
                            finish();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}