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
    private TextView textView,incrementText;
    private Button saveBtn,loadBtn,incrementBtn,decrementBtn;
    private int increment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = findViewById(R.id.userNameEditId);
        password = findViewById(R.id.passEditId);
        textView = findViewById(R.id.textViewId);
        saveBtn = findViewById(R.id.saveBtnId);
        loadBtn = findViewById(R.id.loadBtnId);
        incrementText = findViewById(R.id.incrementTextId);
        incrementBtn = findViewById(R.id.incrementBtnId);
        decrementBtn = findViewById(R.id.decrementBtnId);

        if(loadIncreDecreResult() != 0){
            increment = loadIncreDecreResult();
            incrementText.setText(String.valueOf(increment));
        }


        saveBtn.setOnClickListener(this);
        loadBtn.setOnClickListener(this);

        incrementBtn.setOnClickListener(this);
        decrementBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.saveBtnId){
            String username = userName.getText().toString();
            String pass = password.getText().toString();

            if(username.equals("") && pass.equals("")){
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

        if(v.getId() == R.id.incrementBtnId){
            if(increment>=0){
                ++increment;
                incrementText.setText(String.valueOf(increment));
                saveIncreDecreResult(increment);
            }



        }

        if(v.getId() == R.id.decrementBtnId){
            if(increment>=1){
                --increment;
                incrementText.setText(String.valueOf(increment));
                saveIncreDecreResult(increment);
            }

        }
    }

    private void saveIncreDecreResult(int increment) {
        SharedPreferences preferences = getSharedPreferences("InC_De",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("score",increment);
        editor.apply();
    }
    private int loadIncreDecreResult() {
        SharedPreferences preferences = getSharedPreferences("InC_De",Context.MODE_PRIVATE);
        if(preferences.contains("score")){
           return preferences.getInt("score",0);
        }
        return 0;
    }
}