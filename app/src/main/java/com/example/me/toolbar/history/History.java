package com.example.me.toolbar.history;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.me.R;

public class History extends AppCompatActivity
{
    private ListView history;
    ArrayAdapter<String> adapter;
    String names [] = {"A", "B", "C"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AlertDialog.Builder alertDialog;
        final Dialog dialog = new Dialog(getApplication());
        dialog.setContentView(R.layout.history_layout);
        dialog.setTitle("Title");
        history= (ListView) dialog.findViewById(R.id.history_list);
        dialog.setCancelable(true);
        dialog.setTitle("Title");
        dialog.show();


    }

}
