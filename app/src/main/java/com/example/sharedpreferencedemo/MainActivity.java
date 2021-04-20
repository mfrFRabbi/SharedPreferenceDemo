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

            if(username.equals(null) && pass.equals(null)){
                Toast.makeText(getBaseContext(),"Please give some input!",Toast.LENGTH_SHORT).show();
            }else {
                SharedPreferences sharedPreferences = getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userKey",username);
                editor.putString("passKey",pass);
                editor.commit();
                userName.setText("");
                password.setText("");
                Toast.makeText(getBaseContext(),"Data store successfully done! ",Toast.LENGTH_SHORT).show();

            }

        }
        if(v.getId() == R.id.loadBtnId){
            SharedPreferences sharedPreferences = getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
            if(sharedPreferences.contains("userKey") && sharedPreferences.contains("passKey")){
                String username = sharedPreferences.getString("userKey","Data not found!");
                String pass = sharedPreferences.getString("passKey","Data not found!");
                textView.setText(username+ " " + pass);
            }
        }
    }
}