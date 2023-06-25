package com.example.sushigo;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Register extends BottomSheetDialogFragment{
    private Dbase_SushiGo dbase;

    public static final String TAG = "Register";

    public static Register newInstance(){
        return new Register();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText email = view.findViewById(R.id.etEmail);
        EditText password = view.findViewById(R.id.etPassword);
        EditText repassword = view.findViewById(R.id.etRepeatPassword);
        Button daftar = view.findViewById(R.id.btnRegister);

        dbase = new Dbase_SushiGo(getActivity());

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inEmail = email.getText().toString();
                String inPassword = password.getText().toString();
                String inrePassword = repassword.getText().toString();

                if (!(inrePassword.equals(inPassword))){
                    repassword.setError("Password don't Match");
                }else {
                    Boolean daftar = dbase.simpanUser(inEmail, inPassword);
                    if (daftar == true){
                        Toast.makeText(getActivity(), "Login Success", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_LONG).show();
                    }
                    dismiss();
                }
            }
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();
        if (activity instanceof OnDialogCloseListener){
            ((OnDialogCloseListener) activity).onDialogClose(dialog);
        }
    }
}
