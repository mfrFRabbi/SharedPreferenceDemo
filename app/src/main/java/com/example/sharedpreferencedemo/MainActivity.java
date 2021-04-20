package com.example.sharedpreferencedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText userName,password;
    private TextView textView;
    private Button saveBtn,loadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = findViewById(R.id.userNameEditId);
        password = findViewById(R.id.passEditId);
        textView = findViewById(R.id.textViewId);
        saveBtn = findViewById(R.id.saveBtnId);
        loadBtn = findViewById(R.id.loadBtnId);

        saveBtn.setOnClickListener(this);
        loadBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.saveBtnId){
            String username = userName.getText().toString();
            String pass = password.getText().toString();


        }
        if(v.getId() == R.id.loadBtnId){

        }
    }
}