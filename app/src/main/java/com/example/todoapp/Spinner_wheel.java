package com.example.todoapp;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Spinner_wheel {

    public static void setupSpinnerAdapter(Spinner spinner, int arrayResource, int itemLayoutResource, int dropdownLayoutResource, Context context) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                context,
                arrayResource,
                itemLayoutResource);
        adapter.setDropDownViewResource(dropdownLayoutResource);
        spinner.setAdapter(adapter);
    }
}
