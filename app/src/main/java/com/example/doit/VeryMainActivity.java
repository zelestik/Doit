package com.example.doit;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.Fragment;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import com.example.doit.Fragments.AllFragment;
import com.example.doit.Fragments.TodayFragment;
import com.example.doit.Fragments.WeekFragment;
import com.example.doit.Fragments.dummy.DummyContent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.example.doit.DBHelper.KEY_ALLLABS;
import static com.example.doit.DBHelper.KEY_DATE;
import static com.example.doit.DBHelper.KEY_DONELABS;
import static com.example.doit.DBHelper.KEY_ID;
import static com.example.doit.DBHelper.KEY_LESSON;
import static com.example.doit.DBHelper.KEY_TYPE;
import static com.example.doit.NewTaskActivity.dbhelper;

public class VeryMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    static DBHelper dbHelper;
    TodayFragment tFragment;
    WeekFragment wFragment;
    AllFragment aFragment;
    final String LOG_TAG = "myLogs";
    public static ArrayList<String> lessons = new ArrayList<String>();
    public static ArrayList<Integer> alllabs = new ArrayList<Integer>();
    public static ArrayList<Integer> donelabs = new ArrayList<Integer>();
    public static ArrayList<Long> date = new ArrayList<>();
    public static ArrayList<Integer> id = new ArrayList<>();
    public static ArrayList<String> lessons_done = new ArrayList<String>();
    public static ArrayList<Integer> alllabs_done = new ArrayList<Integer>();
    public static ArrayList<Integer> donelabs_done = new ArrayList<Integer>();
    public static ArrayList<Long> date_done = new ArrayList<>();
    public static ArrayList<Integer> id_done = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tFragment = new TodayFragment();
        wFragment = new WeekFragment();
        aFragment = new AllFragment();
        setContentView(R.layout.activity_very_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        FragmentTransaction ftrans = getSupportFragmentManager().beginTransaction();
        ftrans.replace(R.id.container, tFragment);
        ftrans.commit();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VeryMainActivity.this, NewTaskActivity.class);
                startActivity(intent);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("TODO", null, null, null, "date", null, null);

        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int allLabsIndex = c.getColumnIndex("alllabs");
            int doneLabsIndex = c.getColumnIndex("donelabs");
            int nameColIndex = c.getColumnIndex("lesson");
            int dateColIndex = c.getColumnIndex("date");
            int idColIndex = c.getColumnIndex(KEY_ID);
            do {
                if (c.getInt(allLabsIndex) > c.getInt(doneLabsIndex)) {
                    // получаем значения по номерам столбцов и пишем все в лог
                    alllabs.add(c.getInt(allLabsIndex));
                    donelabs.add(c.getInt(doneLabsIndex));
                    lessons.add(c.getString(nameColIndex));
                    date.add(c.getLong(dateColIndex));
                    id.add(c.getInt(idColIndex));
                }
                else {
                    // получаем значения по номерам столбцов и пишем все в лог
                    alllabs_done.add(c.getInt(allLabsIndex));
                    donelabs_done.add(c.getInt(doneLabsIndex));
                    lessons_done.add(c.getString(nameColIndex));
                    date_done.add(c.getLong(dateColIndex));
                    id_done.add(c.getInt(idColIndex));
                }
            } while (c.moveToNext());
        } else
            Log.d(LOG_TAG, "0 rows");
        c.close();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.very_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentTransaction ftrans = getSupportFragmentManager().beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            ftrans.replace(R.id.container, tFragment);
        } else if (id == R.id.nav_week) {
            ftrans.replace(R.id.container, wFragment);
        }
        ftrans.commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void addToDB(int position){

    }

    public static void onButtonPress (int position) {
    }
}
