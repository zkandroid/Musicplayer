package com.example.administrator.firstapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class dialog extends AppCompatActivity {

    Intent intent;

    private EditText dialog_courseName;
    private EditText dialog_address;
    private RadioButton dialog_single;
    private RadioButton dialog_couple;
    private RadioButton dialog_singleAndCouple;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);


    }


}
