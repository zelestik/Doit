package com.example.doit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewTaskActivity extends AppCompatActivity {

    Button buAdd;
    EditText editLessonAdd, editLabsAdd, editDateAdd;
    DBHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        buAdd = (Button) FindViewById(R.id.buAdd);
        buAdd.setOnClickListener(this);
        editLessonAdd = (EditText) FindViewById(R.id.editLessonAdd);
        editLabsAdd = (EditText) FindViewById(R.id.editLabsAdd);
        editDateAdd = (EditText) FindViewById(R.id.editDateAdd);
        dbhelper = new DBHelper(NewTaskActivity.this);
    }
    @Override
    public void onClick (view view){
        string lesson = editLessonAdd.gerText().toString();

    }
}

