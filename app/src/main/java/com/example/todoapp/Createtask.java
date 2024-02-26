package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.todoapp.utills.CommanUtilities;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Createtask extends AppCompatActivity {

     EditText title, datePickerInput;
    Spinner dropDownMenu;

    ImageView img;
    Button btn;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_createtask);

        datePickerInput = findViewById(R.id.datePickerInput);

         dropDownMenu = findViewById(R.id.dropdownMenu);

        title = findViewById(R.id.title);


        img =  findViewById(R.id.backButton);
        btn = findViewById(R.id.createBtn);


//        setupSpinnerAdapter(dropDownMenu, R.array.menu_items_array);
        //Spinner_wheel spinnerWheel = new Spinner_wheel();
        Spinner_wheel.setupSpinnerAdapter(
                dropDownMenu,
                R.array.menu_items_array,
                android.R.layout.simple_spinner_item,
                android.R.layout.simple_spinner_dropdown_item,
                this
        );





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Title =title.getText().toString();
                String DatePickerInput = datePickerInput.getText().toString();
                String DropdownMenu = dropDownMenu.getSelectedItem().toString();
                Map<String, Object> user = new HashMap<>();
                user.put("Title",Title);
                user.put("DatePicker",DatePickerInput);
                user.put("dropdownmenu", DropdownMenu);

                db.collection("user")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(Createtask.this, "Successfull", Toast.LENGTH_SHORT).show();
                                newScreen2(v);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Createtask.this, "FAILED", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

//                btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                newScreen2(v);
//            }
//        });

    }

    public void showDatePickerDialog(View view) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(

                this,R.style.CustomDatePickerDialogTheme,
                (datePicker, year1, month1, day) -> {
                    // Update the EditText with the selected date
                    String selectedDate = day + "/" + (month1 + 1) + "/" + year1;
                    datePickerInput.setText(selectedDate);
                },

                year, month, dayOfMonth);
        datePickerDialog.show();
    }

    public void newScreen(View v) {
        CommanUtilities.startActivity(LoginActivity.class, this);
    }

    public void newScreen2(View v) {
        CommanUtilities.startActivity(TaskItemList.class, this);
    }
}