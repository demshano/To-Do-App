package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.todoapp.utills.CommanUtilities;

public class LoginActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize the button correctly
        button = findViewById(R.id.loginbtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, Createtask.class);
                startActivity(i);
            }
        });
    }

    public void newScreen(View v) {
        CommanUtilities.startActivity(Createtask.class, this);
    }

}
