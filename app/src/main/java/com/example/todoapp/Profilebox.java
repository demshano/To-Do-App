package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class Profilebox extends AppCompatActivity{

    Button datePickerButton;
    DatePickerDialog datePickerDialog;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_item_list);


        datePickerButton = findViewById(R.id.datePickerButton);

        // Set onClickListener for datePickerButton
        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });




    }


    private void showDatePickerDialog() {
        // Get current date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        // Create and show DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(Profilebox.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Display selected date in Toast
                        Toast.makeText(getApplicationContext(),
                                dayOfMonth + "-" + (monthOfYear + 1) + "-" + year,
                                Toast.LENGTH_LONG).show();
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

}