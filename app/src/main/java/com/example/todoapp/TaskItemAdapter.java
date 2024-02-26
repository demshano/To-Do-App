package com.example.todoapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TaskItemAdapter extends ArrayAdapter<Taskitem> {

    private Context Tcontext;
    private int Tresource;
//    public TaskItemAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Taskitem> objects) {
//        super(context, resource, objects);
//
//        this.Tcontext=context;
//        this.Tresource=resource;
//    }

    public TaskItemAdapter(@NonNull Context context, int resource, @NonNull List<Taskitem> objects) {
        super(context, resource, objects);

        this.Tcontext = context;
        this.Tresource = resource;
    }


    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(Tcontext);
        convertView = layoutInflater.inflate(Tresource, parent,false);

        TextView txt1 =convertView.findViewById(R.id.txt1);
        TextView txt2 =convertView.findViewById(R.id.txt2);
        TextView txt3 =convertView.findViewById(R.id.txt3);
        ImageView icon1 = convertView.findViewById(R.id.icon1);

        txt1.setText(getItem(position).getTaskname1());
        txt2.setText(getItem(position).getTaskname2());
        txt3.setText(getItem(position).getTaskname3());
        icon1.setImageResource(getItem(position).getTaskname4());
        return convertView;
    }
}
