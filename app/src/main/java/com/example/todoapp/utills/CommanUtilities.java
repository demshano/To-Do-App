package com.example.todoapp.utills;

import android.content.Context;
import android.content.Intent;

public class CommanUtilities {

    //start new activity

    public static void startActivity(Class clz, Context context){
        Intent i = new Intent(context, clz);
        context.startActivity(i);

    }
}
