package com.example.doit;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doit.Fragments.MyTodayRecyclerViewAdapter;
import com.example.doit.Fragments.TodayFragment;
import com.example.doit.Fragments.dummy.DummyContent;

import java.util.Calendar;

import static com.example.doit.Fragments.dummy.DummyContent.ITEMS;
import static com.example.doit.Fragments.dummy.DummyContent.addItem;
import static com.example.doit.Fragments.dummy.DummyContent.createDummyItem;
import static com.example.doit.VeryMainActivity.alllabs;
import static com.example.doit.VeryMainActivity.date;
import static com.example.doit.VeryMainActivity.donelabs;
import static com.example.doit.VeryMainActivity.lessons;
import static com.example.doit.VeryMainActivity.id;

public class NewTaskActivity extends AppCompatActivity  {

    private Button button;
    private EditText editLessonAdd, editLabsAdd, editDateAdd;
    static DBHelper dbhelper;
    private CalendarView calendarView;
    public long dateint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        editLessonAdd = findViewById(R.id.editLessonAdd);
        editLabsAdd = findViewById(R.id.editLabsAdd);
        calendarView = findViewById(R.id.calendarView);
        button = findViewById(R.id.buAdd);
        dateint = calendarView.getDate();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                Calendar c = Calendar.getInstance();
                c.set(year, month, day);
                dateint =  c.getTimeInMillis();
            }
        });
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                dbhelper = new DBHelper(NewTaskActivity.this);
                if ((editLessonAdd.getText().length() != 0) & (editLabsAdd.getText().length() != 0)) {
                    String lesson = editLessonAdd.getText().toString();
                    String labstr = editLabsAdd.getText().toString();
                    int labint = Integer.parseInt(labstr);
                    SQLiteDatabase db = dbhelper.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DBHelper.KEY_LESSON, lesson);
                    contentValues.put(DBHelper.KEY_ALLLABS, labint);
                    contentValues.put(DBHelper.KEY_DONELABS, 0);
                    contentValues.put(DBHelper.KEY_DATE, dateint);
                    contentValues.put(DBHelper.KEY_TYPE, 1);
                    if (labint > 0) {
                        long newRowId = db.insert(DBHelper.TABLE_TODO, null, contentValues);
                        // Если ID  -1, значит произошла ошибка
                        if (newRowId == -1) {
                            Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
                        } else {
                            alllabs.add(labint);
                            donelabs.add(0);
                            lessons.add(lesson);
                            date.add(dateint);
                            id.add((int) newRowId);
                            Toast.makeText(getApplicationContext(), "Успешно", Toast.LENGTH_SHORT).show();
                            ITEMS.clear();
                            for (int i = 1; i <= lessons.size(); i++) {
                                addItem(createDummyItem(i));
                            }
                            Intent intent = new Intent();
                            setResult(Activity.RESULT_OK, intent);
                            finish();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Количество работ должно быть больше 0", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(getApplicationContext(), "Поля не заполнены", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


