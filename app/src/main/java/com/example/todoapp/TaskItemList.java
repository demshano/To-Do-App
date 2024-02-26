package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.todoapp.utills.CommanUtilities;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TaskItemList extends AppCompatActivity {

    ListView listview;
    FirebaseFirestore db;

    Button datePickerButton,addBtn;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_item_list);
        db = FirebaseFirestore.getInstance();
        listview  = findViewById(R.id.listviewbox);

        retrieveDataFromFirestore();

//       ---------------- here code change----------------
//        ArrayList<Taskitem> arraylist = new ArrayList<>();
//
//        arraylist.add(new Taskitem("User interview","Mon 22 Jan 2023","High",R.drawable.red_dot));
//        arraylist.add(new Taskitem("Daily Standup Meeting","Mon 22 Jan 2023","Low",R.drawable.green_dot));
//        arraylist.add(new Taskitem("Team Discussion","Mon 22 Jan 2023","High",R.drawable.red_dot));
//        arraylist.add(new Taskitem("User Interview","Mon 22 Jan 2023","Medium",R.drawable.orange_dot));
//
//
//        TaskItemAdapter taskitemadapter = new TaskItemAdapter(this, R.layout.activity_row_list, arraylist);
//
//        listview.setAdapter(taskitemadapter);

        //------------here code change end--------------------
        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newScreen(v);
            }
        });

        datePickerButton = findViewById(R.id.datePickerButton);

        // Set onClickListener for datePickerButton
        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        Spinner prioritySpinner = findViewById(R.id.prioritySpinner);

        // Create a list of priorities
        List<String> priorities = new ArrayList<>();
        priorities.add("Priority");
        priorities.add("High");
        priorities.add("Medium");
        priorities.add("Low");

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, priorities);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        prioritySpinner.setAdapter(adapter);

    }

    private void retrieveDataFromFirestore() {
        // Query the "user" collection
        db.collection("user")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // Process each document in the result set
                            List<Taskitem> taskItems = new ArrayList<>();
                            int drawableResource=R.drawable.green_dot;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Retrieve data from the document
                                String title = document.getString("Title");
                                String datePicker = document.getString("DatePicker");
                                String dropdownMenu = document.getString("dropdownmenu");


                                switch(dropdownMenu){
                                    case "High":
                                        drawableResource = R.drawable.red_dot;
                                        break;

                                    case "Medium":
                                        drawableResource  = R.drawable.orange_dot;
                                        break;

                                    case "Low":
                                        drawableResource = R.drawable.green_dot;
                                        break;
                                }

                                // Add data to list
                                taskItems.add(new Taskitem(title, datePicker, dropdownMenu, drawableResource));
                            }
                            // Update ListView adapter with retrieved data
                            updateListView(taskItems);
                        } else {
                            Log.d("FirestoreData", "Error getting documents: ", task.getException());
                            Toast.makeText(TaskItemList.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateListView(List<Taskitem> taskItems) {
        TaskItemAdapter adapter = new TaskItemAdapter(this, R.layout.activity_row_list, taskItems);
        listview.setAdapter(adapter);
    }




    private void showDatePickerDialog() {
        // Get current date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        // Create and show DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(TaskItemList.this,
                R.style.CustomDatePickerDialogTheme,
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

    public void newScreen(View v) {
        CommanUtilities.startActivity(Createtask.class, this);
    }
}