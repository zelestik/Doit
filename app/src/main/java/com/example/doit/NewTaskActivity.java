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
    EditText editLessonAdd, editLabsAdd, editDateAdd;
    DBHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbhelper = new DBHelper(NewTaskActivity.this);
        setContentView(R.layout.activity_new_task);
        button = (Button) findViewById(R.id.buAdd);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                EditText lesson = findViewById(R.id.editLessonAdd);
                EditText labstr = findViewById(R.id.editLabsAdd);
                EditText datestr = findViewById(R.id.editDateAdd);
                int labint = Integer.parseInt(labstr.toString());
                int dateint = Integer.parseInt(datestr.toString());
                SQLiteDatabase database = dbhelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                //switch (v.getId()){
                //case R.id.buAdd:
                contentValues.put(DBHelper.KEY_LESSON, lesson.toString());
                contentValues.put(DBHelper.KEY_ALLLABS, labint);
                contentValues.put(DBHelper.KEY_DATE, dateint);
                //break;
            }
        });
    }
}


