package com.example.doit;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doit.Fragments.TodayFragment;

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
                String lesson = editLessonAdd.getText().toString();
                String labstr = editLabsAdd.getText().toString();
                String datestr = editDateAdd.getText().toString();
                int labint = Integer.parseInt(labstr);
                int dateint = Integer.parseInt(datestr);
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBHelper.KEY_LESSON, lesson);
                contentValues.put(DBHelper.KEY_ALLLABS, labint);
                contentValues.put(DBHelper.KEY_DONELABS, 0);
                contentValues.put(DBHelper.KEY_DATE, dateint);
                contentValues.put(DBHelper.KEY_TYPE, 1);
                long newRowId = db.insert(DBHelper.TABLE_TODO, null, contentValues);
                // Если ID  -1, значит произошла ошибка
                if (newRowId == -1) {
                    Toast.makeText(getApplicationContext(), "Ошибка " + lesson + dateint + labint, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Успешно", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


