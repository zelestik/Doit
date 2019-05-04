package com.example.doit;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewTaskActivity extends AppCompatActivity {

    private Button button;
    private EditText editLessonAdd, editLabsAdd, editDateAdd;
    DBHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        dbhelper = new DBHelper(NewTaskActivity.this);
        editLessonAdd = findViewById(R.id.editLessonAdd);
        editLabsAdd = findViewById(R.id.editLabsAdd);
        editDateAdd = findViewById(R.id.editDateAdd);
        button = findViewById(R.id.buAdd);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                //EditText lesson =
                //EditText labstr =
                //EditText datestr =
                String lesson = editLessonAdd.getText().toString();
                String labstr = editLabsAdd.getText().toString();
                String datestr = editDateAdd.getText().toString();
                int labint = Integer.parseInt(labstr);
                int dateint = Integer.parseInt(datestr);
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                //switch (v.getId()){
                //case R.id.buAdd:
                contentValues.put(DBHelper.KEY_LESSON, lesson);
                contentValues.put(DBHelper.KEY_ALLLABS, labint);
                contentValues.put(DBHelper.KEY_DATE, dateint);
                //break;
            }
        });
    }
}


